package com.dautofreitas.votacaoexecicio.domain.interfaces.service;

import com.dautofreitas.votacaoexecicio.domain.entity.Associado;
import com.dautofreitas.votacaoexecicio.domain.entity.Pauta;

import java.util.List;
import java.util.UUID;

public interface PautaService {
    Pauta salvar(Pauta pauta);
    Pauta buscarPorId(UUID pautaId);
    List<Pauta> buscarTodos();
}
