package com.dautofreitas.votacaoexecicio.domain.exeptions;

import java.util.UUID;

public class RecursoNaoExisteException extends NegocioException {
    public RecursoNaoExisteException(String message) {
        super(String.format("%s Não existe", message));
    }

    public RecursoNaoExisteException(String message, UUID objetoId) {
        super(String.format("%s com Id %s Não existe", message,objetoId));
    }
}
