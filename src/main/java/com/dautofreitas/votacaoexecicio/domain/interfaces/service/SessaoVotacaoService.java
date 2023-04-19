package com.dautofreitas.votacaoexecicio.domain.interfaces.service;

import com.dautofreitas.votacaoexecicio.application.dtos.SessaoVotacaoDto;
import com.dautofreitas.votacaoexecicio.domain.entity.ResultadoVotacao;
import com.dautofreitas.votacaoexecicio.domain.entity.SessaoVotacao;
import com.dautofreitas.votacaoexecicio.domain.enums.OpcaoVoto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SessaoVotacaoService {
    SessaoVotacao abrirVotacao(Integer tempoVotacaoEmSegundos, UUID pautaId, LocalDateTime momentoVotacao);

    void votar(UUID pautaId, UUID associadoId, OpcaoVoto opcao, LocalDateTime momentoVoto);
    ResultadoVotacao retornaResultado(UUID sessaoId);

    List<SessaoVotacao> buscarTodos();
}
