package com.dautofreitas.votacaoexecicio.domain.exeptions;

public class AssociadoJaVotouException extends NegocioException{

    public AssociadoJaVotouException() {
        super("Associado já possui voto na pauta");
    }
}
