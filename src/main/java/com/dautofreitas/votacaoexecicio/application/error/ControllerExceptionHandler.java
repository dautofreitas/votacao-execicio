package com.dautofreitas.votacaoexecicio.application.error;

import com.dautofreitas.votacaoexecicio.domain.exeptions.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<MensagemErro> handle(NegocioException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MensagemErro(exception.getMessage()));
    }
}
