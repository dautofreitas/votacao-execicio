package com.dautofreitas.votacaoexecicio.infra.JpaInterfaces;

import com.dautofreitas.votacaoexecicio.infra.entity.EntityPauta;
import com.dautofreitas.votacaoexecicio.infra.entity.EntityVoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VotoRepositoryJpa extends JpaRepository<EntityVoto, UUID> {
}
