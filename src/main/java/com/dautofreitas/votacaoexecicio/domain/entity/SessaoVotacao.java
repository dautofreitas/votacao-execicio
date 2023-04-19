package com.dautofreitas.votacaoexecicio.domain.entity;

import com.dautofreitas.votacaoexecicio.domain.enums.SessaoVotacaoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@Data
public class SessaoVotacao {
    private UUID id;
    private Pauta pauta;
    private List<Voto> votos;
    private SessaoVotacaoStatus status;
    private LocalDateTime momentoInicio;
    private int tempoVotacaoEmSegundos;


}
