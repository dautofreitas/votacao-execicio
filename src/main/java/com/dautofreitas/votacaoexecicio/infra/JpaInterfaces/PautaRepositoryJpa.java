package com.dautofreitas.votacaoexecicio.infra.JpaInterfaces;

import com.dautofreitas.votacaoexecicio.infra.entity.EntityPauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PautaRepositoryJpa extends JpaRepository<EntityPauta, UUID> {
}
