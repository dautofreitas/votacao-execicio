package com.dautofreitas.votacaoexecicio.domain.entity;

import com.dautofreitas.votacaoexecicio.domain.enums.OpcaoVoto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@Data
public class Voto {
    private UUID id;
    private OpcaoVoto opcao;
    private Associado associado;
    private LocalDateTime momentoVoto;
}
