package com.dautofreitas.votacaoexecicio.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@AllArgsConstructor
@Data
public class Associado {
    private UUID id;
    private String nome;
}
