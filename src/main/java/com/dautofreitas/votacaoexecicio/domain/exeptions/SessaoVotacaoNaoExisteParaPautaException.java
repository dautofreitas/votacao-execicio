package com.dautofreitas.votacaoexecicio.domain.exeptions;

import java.util.UUID;

public class SessaoVotacaoNaoExisteParaPautaException extends NegocioException{

    public SessaoVotacaoNaoExisteParaPautaException(UUID pautaId) {
        super(String.format("A sessão de votação não encontrada para a pauta com id %s",pautaId ));
    }
}
