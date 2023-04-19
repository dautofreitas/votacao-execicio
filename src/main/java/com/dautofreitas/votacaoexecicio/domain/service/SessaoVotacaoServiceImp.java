package com.dautofreitas.votacaoexecicio.domain.service;

import com.dautofreitas.votacaoexecicio.domain.exeptions.*;
import com.dautofreitas.votacaoexecicio.domain.interfaces.repository.SessaoVotacaoRepository;
import com.dautofreitas.votacaoexecicio.domain.interfaces.repository.VotoRepository;
import com.dautofreitas.votacaoexecicio.domain.interfaces.service.AssociadoService;
import com.dautofreitas.votacaoexecicio.domain.interfaces.service.PautaService;
import com.dautofreitas.votacaoexecicio.domain.interfaces.service.SessaoVotacaoService;
import com.dautofreitas.votacaoexecicio.domain.entity.*;
import com.dautofreitas.votacaoexecicio.domain.enums.OpcaoVoto;
import com.dautofreitas.votacaoexecicio.domain.enums.SessaoVotacaoStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@Service
public class SessaoVotacaoServiceImp implements SessaoVotacaoService {
    private SessaoVotacaoRepository sessaoVotacaoRespository;

    private VotoRepository votoRespository;
    private PautaService pautaService;
    private AssociadoService associadoService;


    public static final int tempoVotacaoEmSegundosDefault= 60;

    @Override
    public SessaoVotacao abrirVotacao(Integer tempoVotacaoEmSegundos, UUID pautaId, LocalDateTime momentoVotacao){


        Pauta pauta = pautaService.buscarPorId(pautaId);

        int tempoVotacao =  tempoVotacaoEmSegundos==null?tempoVotacaoEmSegundosDefault: tempoVotacaoEmSegundos;

        SessaoVotacao sessaoVotacao = new SessaoVotacao(null, pauta, new ArrayList<>(), SessaoVotacaoStatus.ABERTA,
                momentoVotacao, tempoVotacao);

       return sessaoVotacaoRespository.salvar(sessaoVotacao);

    }
    @Override
    public void votar(UUID pautaId, UUID associadoId, OpcaoVoto opcao, LocalDateTime momentoVoto)
    {

        SessaoVotacao sessaoVotacao = sessaoVotacaoRespository.buscarSessaoVotacaoAbertaPorPautaId(pautaId)
                .orElseThrow(()-> new SessaoVotacaoNaoExisteParaPautaException(pautaId));

        if(votoJaExiste(sessaoVotacao, associadoId))
        {
            throw new AssociadoJaVotouException();
        }

        Duration duracao = Duration.between(sessaoVotacao.getMomentoInicio(), momentoVoto);

        if(duracao.toSeconds() > sessaoVotacao.getTempoVotacaoEmSegundos())
        {
            sessaoVotacao.setStatus(SessaoVotacaoStatus.FECHADA);
            sessaoVotacaoRespository.salvar(sessaoVotacao);

            throw new TempoParaVotacaoJaAcabouException();
        }
        Associado associado = associadoService.buscarPorId(associadoId);

        Voto voto  = new Voto(null, opcao, associado, momentoVoto);

        votoRespository.salvar(voto, sessaoVotacao);
    }
    @Override
    public ResultadoVotacao retornaResultado(UUID sessaoId)
    {
        SessaoVotacao sessaoVotacao = sessaoVotacaoRespository.buscarSessaoVotacaoPorId(sessaoId)
                .orElseThrow(() -> new ResultadoVotacaoNaoExisteException());

        long totalVostosNegativos = 0;
        long totalVostosPositivos = 0;
        for ( Voto voto: sessaoVotacao.getVotos())
       {
           if(voto.getOpcao() == OpcaoVoto.SIM)
               totalVostosPositivos++;
           else{
               totalVostosNegativos++;
           }
       }

       return new ResultadoVotacao(totalVostosPositivos, totalVostosNegativos, sessaoVotacao);
    }

    @Override
    public List<SessaoVotacao> buscarTodos() {
        return sessaoVotacaoRespository.buscarTodos();
    }

    private boolean votoJaExiste(SessaoVotacao sessaoVotacao, UUID associadoId){

        return sessaoVotacao.getVotos().stream().anyMatch(a -> a.getAssociado().getId().equals(associadoId));
    }

}
