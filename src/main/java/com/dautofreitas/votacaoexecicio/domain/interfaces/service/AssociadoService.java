package com.dautofreitas.votacaoexecicio.domain.interfaces.service;

import com.dautofreitas.votacaoexecicio.domain.entity.Associado;

import java.util.List;
import java.util.UUID;

public interface AssociadoService {
    Associado salvar(Associado associado);

    Associado buscarPorId(UUID associadoId);
    List<Associado> buscarTodos();

}
