package com.dautofreitas.votacaoexecicio.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@AllArgsConstructor
@Data
public class ResultadoVotacao {
    private long totalVotosPositivos;
    private long totalVotosNegativos;
    private SessaoVotacao sessaoVotacao;
}
