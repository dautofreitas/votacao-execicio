package com.dautofreitas.votacaoexecicio.domain.interfaces.repository;

import com.dautofreitas.votacaoexecicio.domain.entity.Pauta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PautaRepository {
    Optional<Pauta> buscarPorId(UUID pautaId);
    Pauta salvar(Pauta pauta);
    List<Pauta> buscarTodos();


}
