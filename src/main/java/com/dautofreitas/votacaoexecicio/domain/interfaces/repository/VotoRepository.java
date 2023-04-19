package com.dautofreitas.votacaoexecicio.domain.interfaces.repository;

import com.dautofreitas.votacaoexecicio.domain.entity.SessaoVotacao;
import com.dautofreitas.votacaoexecicio.domain.entity.Voto;


public interface VotoRepository {
    void salvar(Voto voto, SessaoVotacao sessaoVotacao);

}
