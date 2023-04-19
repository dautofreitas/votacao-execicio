package com.dautofreitas.votacaoexecicio.application.controllers;

import com.dautofreitas.votacaoexecicio.application.dtos.PautaDto;
import com.dautofreitas.votacaoexecicio.domain.interfaces.service.PautaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/pauta")
public class PautaController {

    private PautaService service;
    @GetMapping()
    public ResponseEntity<List<PautaDto>> buscaTodos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.buscarTodos().stream()
                        .map(PautaDto::fromDomain).toList());
    }
    @GetMapping(path = "/{pautaId}")
    public ResponseEntity<PautaDto> buscaPorId( @PathVariable UUID pautaId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(PautaDto.fromDomain(service.buscarPorId(pautaId)));
    }

    @PostMapping()
    public ResponseEntity<PautaDto> salvar(@RequestBody PautaDto pauta) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PautaDto.fromDomain(service.salvar(PautaDto.toDomain(pauta))));
    }
}
