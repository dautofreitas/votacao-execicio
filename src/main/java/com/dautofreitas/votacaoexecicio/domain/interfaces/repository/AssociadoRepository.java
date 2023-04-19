package com.dautofreitas.votacaoexecicio.domain.interfaces.repository;

import com.dautofreitas.votacaoexecicio.domain.entity.Associado;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
public interface AssociadoRepository {
    Optional<Associado> buscarPorId(UUID associadoId);
    Associado salvar(Associado associado);
    List<Associado> buscarTodos();
}
