package com.dautofreitas.votacaoexecicio.domain.interfaces.repository;

import com.dautofreitas.votacaoexecicio.domain.entity.SessaoVotacao;
import com.dautofreitas.votacaoexecicio.domain.entity.Voto;
import com.dautofreitas.votacaoexecicio.domain.enums.SessaoVotacaoStatus;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessaoVotacaoRepository {
    SessaoVotacao salvar(SessaoVotacao sessaoVotacao);
    Optional<SessaoVotacao> buscarSessaoVotacaoAbertaPorPautaId(UUID pautaId);
    Optional<SessaoVotacao> buscarSessaoVotacaoPorId(UUID sessaoId);

    List<SessaoVotacao> buscarTodos();
}
