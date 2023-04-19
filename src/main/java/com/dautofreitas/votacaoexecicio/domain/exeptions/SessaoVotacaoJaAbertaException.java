package com.dautofreitas.votacaoexecicio.domain.exeptions;

public class SessaoVotacaoJaAbertaException extends NegocioException{

    public SessaoVotacaoJaAbertaException() {
        super("A sessão de votação já está aberta não é possível abrir novamente");
    }
}
