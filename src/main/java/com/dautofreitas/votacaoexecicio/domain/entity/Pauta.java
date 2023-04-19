package com.dautofreitas.votacaoexecicio.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@AllArgsConstructor
@Data
public class Pauta {
    private UUID id;
    private String nome;
    private String descricao;
}
