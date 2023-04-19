package com.dautofreitas.votacaoexecicio.domain.exeptions;

public class ResultadoVotacaoNaoExisteException extends NegocioException{

    public ResultadoVotacaoNaoExisteException() {
        super("Resultado Não existe");
    }
}
