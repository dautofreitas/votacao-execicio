package com.dautofreitas.votacaoexecicio.application.controllers;

import com.dautofreitas.votacaoexecicio.application.dtos.AssociadoDto;
import com.dautofreitas.votacaoexecicio.domain.interfaces.service.AssociadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/associado")
public class AssociadoController {

    private AssociadoService service;
    @GetMapping()
    public ResponseEntity<List<AssociadoDto>> buscaTodos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.buscarTodos().stream()
                        .map(AssociadoDto::fromDomain).toList());
    }
    @GetMapping(path = "/{associadoId}")
    public ResponseEntity<AssociadoDto> buscaPorId( @PathVariable UUID associadoId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(AssociadoDto.fromDomain(service.buscarPorId(associadoId)));
    }

    @PostMapping()
    public ResponseEntity<AssociadoDto> salvar(@RequestBody AssociadoDto associado) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AssociadoDto.fromDomain(service.salvar(AssociadoDto.toDomain(associado))));
    }
}
