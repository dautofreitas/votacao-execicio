package com.dautofreitas.votacaoexecicio.domain.exeptions;

public abstract class NegocioException extends RuntimeException {
    public NegocioException(String message){
        super(message);
    }
}
