package com.dautofreitas.votacaoexecicio.domain.exeptions;

public class TempoParaVotacaoJaAcabouException extends NegocioException{

    public TempoParaVotacaoJaAcabouException() {
        super("Tempo para votação já acabou");
    }
}
