package com.dautofreitas.votacaoexecicio.application.controllers;

import com.dautofreitas.votacaoexecicio.application.dtos.ResultadoVotacaoDto;
import com.dautofreitas.votacaoexecicio.application.dtos.SessaoVotacaoDto;
import com.dautofreitas.votacaoexecicio.domain.enums.OpcaoVoto;
import com.dautofreitas.votacaoexecicio.domain.interfaces.service.SessaoVotacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/sessao")
public class SessaoVotacaoController {
    private SessaoVotacaoService service;


    @GetMapping("/abrir-votacao")
    public ResponseEntity<SessaoVotacaoDto> abrirVotacao(@RequestParam(required=false) Integer tempo, @RequestParam UUID pautaId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SessaoVotacaoDto.fromDomain(service.abrirVotacao(tempo, pautaId, LocalDateTime.now())));

    }

    @GetMapping("/votar")
    public ResponseEntity<SessaoVotacaoDto> votar(
            @RequestParam UUID pautaId,
            @RequestParam UUID associadoId,
            OpcaoVoto opcao) {

        service.votar(pautaId, associadoId, opcao, LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();

    }

    @GetMapping("/resultado")
    public ResponseEntity<ResultadoVotacaoDto> votar(
            @RequestParam UUID sessaoId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultadoVotacaoDto.fromDomain(service.retornaResultado(sessaoId)));

    }

    @GetMapping()
    public ResponseEntity<List<SessaoVotacaoDto>> buscaTodos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.buscarTodos().stream()
                        .map(SessaoVotacaoDto::fromDomain).toList());
    }
}
