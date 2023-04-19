package com.dautofreitas.votacaoexecicio.infra.JpaInterfaces;

import com.dautofreitas.votacaoexecicio.infra.entity.EntityAssociado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssociadoRepositoryJpa extends JpaRepository<EntityAssociado, UUID>  {
}
