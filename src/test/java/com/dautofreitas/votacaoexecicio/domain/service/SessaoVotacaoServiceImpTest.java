package com.dautofreitas.votacaoexecicio.domain.service;

import com.dautofreitas.votacaoexecicio.domain.entity.*;
import com.dautofreitas.votacaoexecicio.domain.interfaces.repository.SessaoVotacaoRepository;
import com.dautofreitas.votacaoexecicio.domain.enums.OpcaoVoto;
import com.dautofreitas.votacaoexecicio.domain.enums.SessaoVotacaoStatus;
import com.dautofreitas.votacaoexecicio.domain.exeptions.AssociadoJaVotouException;
import com.dautofreitas.votacaoexecicio.domain.exeptions.RecursoNaoExisteException;
import com.dautofreitas.votacaoexecicio.domain.exeptions.TempoParaVotacaoJaAcabouException;
import com.dautofreitas.votacaoexecicio.domain.interfaces.repository.VotoRepository;
import com.dautofreitas.votacaoexecicio.domain.interfaces.service.AssociadoService;
import com.dautofreitas.votacaoexecicio.domain.interfaces.service.PautaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessaoVotacaoServiceImpTest {
    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;
    @Mock
    private VotoRepository votoRepository;
    @Mock
    private PautaService pautaService;
    @Mock
    private AssociadoService associadoService;
    @Test
    public void deveAbrirVotacaoComTempoVotacaoNull()
    {
        LocalDateTime dateTime = LocalDateTime.now();

        Pauta pauta  = getPautaDefault();

        when(pautaService.buscarPorId(pauta.getId())).thenReturn(pauta);

        SessaoVotacao expected = new SessaoVotacao(null, pauta, new ArrayList<>(), SessaoVotacaoStatus.ABERTA,
                dateTime, SessaoVotacaoServiceImp.tempoVotacaoEmSegundosDefault );

        SessaoVotacaoServiceImp service  = new SessaoVotacaoServiceImp(
                sessaoVotacaoRepository,
                votoRepository,
                pautaService,
                associadoService);

        service.abrirVotacao(null, pauta.getId(), dateTime);

        verify(sessaoVotacaoRepository).salvar(expected);
    }

    @Test
    public void deveAbrirVotacaoComTempoVotacaoFornecido()
    {
        LocalDateTime dateTime = LocalDateTime.now();

        Pauta pauta  = getPautaDefault();
        dateTime.plusSeconds(5);

        int tempoVotacao = 5;

        when(pautaService.buscarPorId(pauta.getId())).thenReturn(pauta);

        SessaoVotacao expected = new SessaoVotacao(null, pauta, new ArrayList<>(), SessaoVotacaoStatus.ABERTA,
                dateTime, tempoVotacao );

        SessaoVotacaoServiceImp service  = new SessaoVotacaoServiceImp(sessaoVotacaoRepository, votoRepository, pautaService, associadoService);

        service.abrirVotacao(tempoVotacao, pauta.getId(), dateTime);

        verify(sessaoVotacaoRepository).salvar(expected);
    }

    @Test
    public void deveFalharAoAbrirVotacaoQuandoNaoExistePauta()
    {
        LocalDateTime dateTime = LocalDateTime.now();
        Pauta pauta  = getPautaDefault();
        when(pautaService.buscarPorId(pauta.getId()))
                .thenThrow(new RecursoNaoExisteException(Pauta.class.getSimpleName(), pauta.getId()));

        SessaoVotacaoServiceImp service  = new SessaoVotacaoServiceImp(sessaoVotacaoRepository, votoRepository, pautaService, associadoService);

        assertThatThrownBy(() -> service.abrirVotacao(null, pauta.getId(), dateTime))
                .isInstanceOf(RecursoNaoExisteException.class)
                .hasMessageContaining(String.format("%s com Id %s Não existe", Pauta.class.getSimpleName(),pauta.getId()));
    }

    @Test
    public void deveVotar(){

        Associado associado = new Associado(UUID.randomUUID(), "test");
        Pauta pauta = getPautaDefault();
        LocalDateTime dateTime = LocalDateTime.now();
        SessaoVotacao sessaoVotacao = new SessaoVotacao(UUID.randomUUID(), pauta, new ArrayList<>(), SessaoVotacaoStatus.ABERTA,
                dateTime, SessaoVotacaoServiceImp.tempoVotacaoEmSegundosDefault );
        when(sessaoVotacaoRepository.buscarSessaoVotacaoAbertaPorPautaId(pauta.getId())).thenReturn(Optional.of(sessaoVotacao));


        SessaoVotacaoServiceImp service  = new SessaoVotacaoServiceImp(sessaoVotacaoRepository, votoRepository, pautaService, associadoService);


        service.votar(pauta.getId(), associado.getId(), OpcaoVoto.SIM, dateTime);
    }

    @Test
    public void deveFalharAoVotarQuantoTempoJaAcabou(){

        Associado associado = new Associado(UUID.randomUUID(), "test");
        Pauta pauta = getPautaDefault();
        LocalDateTime inicioSessao = LocalDateTime.now();
        LocalDateTime momentoVotacao = LocalDateTime.now().plusMinutes(2);

        SessaoVotacao sessaoVotacao = new SessaoVotacao(UUID.randomUUID(), pauta, new ArrayList<>(), SessaoVotacaoStatus.ABERTA,
                inicioSessao, SessaoVotacaoServiceImp.tempoVotacaoEmSegundosDefault );
        when(sessaoVotacaoRepository.buscarSessaoVotacaoAbertaPorPautaId(pauta.getId())).thenReturn(Optional.of(sessaoVotacao));


        SessaoVotacaoServiceImp service  = new SessaoVotacaoServiceImp(sessaoVotacaoRepository, votoRepository, pautaService, associadoService);

        assertThatThrownBy(()-> service.votar(pauta.getId(), associado.getId(), OpcaoVoto.SIM,momentoVotacao))
                .isInstanceOf(TempoParaVotacaoJaAcabouException.class)
                .hasMessageContaining("Tempo para votação já acabou");
    }

    @Test
    public void deveFalharAoVotarQuantoVotoJaExiste(){

        Associado associado = new Associado(UUID.randomUUID(), "test");
        Voto voto = new Voto(UUID.randomUUID(), OpcaoVoto.SIM, associado, LocalDateTime.now());
        Pauta pauta = getPautaDefault();
        SessaoVotacao sessaoVotacao = new SessaoVotacao(UUID.randomUUID(), pauta, List.of(voto), SessaoVotacaoStatus.ABERTA,
                LocalDateTime.now(), SessaoVotacaoServiceImp.tempoVotacaoEmSegundosDefault );
        when(sessaoVotacaoRepository.buscarSessaoVotacaoAbertaPorPautaId(pauta.getId())).thenReturn(Optional.of(sessaoVotacao));


        SessaoVotacaoServiceImp service  = new SessaoVotacaoServiceImp(
                sessaoVotacaoRepository, votoRepository, pautaService, associadoService);


        assertThatThrownBy(()-> service.votar(pauta.getId(), associado.getId(), OpcaoVoto.SIM,LocalDateTime.now()))
                .isInstanceOf(AssociadoJaVotouException.class)
                .hasMessageContaining("Associado já possui voto na pauta");
    }
    @Test
    public void deveCalcularResultado()
    {

        Voto votoSim = new Voto(UUID.randomUUID(), OpcaoVoto.SIM, null, LocalDateTime.now());

        Voto votoNao = new Voto(UUID.randomUUID(), OpcaoVoto.NAO, null, LocalDateTime.now());

        SessaoVotacao sessaoVotacao = new SessaoVotacao(UUID.randomUUID(),
                null,
                List.of(votoSim, votoNao),
                SessaoVotacaoStatus.ABERTA,
                LocalDateTime.now(), 60);

        when(sessaoVotacaoRepository.buscarSessaoVotacaoPorId(any())).thenReturn(Optional.of(sessaoVotacao));

        SessaoVotacaoServiceImp service  = new SessaoVotacaoServiceImp(
                sessaoVotacaoRepository, votoRepository, pautaService, associadoService);

        ResultadoVotacao reruntado = service.retornaResultado(sessaoVotacao.getId());

        assertThat(reruntado.getTotalVotosPositivos()).isEqualTo(1);
        assertThat(reruntado.getTotalVotosNegativos()).isEqualTo(1);
    }
    private Pauta getPautaDefault() {
        return new Pauta(UUID.randomUUID(), "teste", "teste");
    }
}
