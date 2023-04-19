package com.dautofreitas.votacaoexecicio.infra.repository;

import com.dautofreitas.votacaoexecicio.domain.entity.Associado;
import com.dautofreitas.votacaoexecicio.domain.interfaces.repository.AssociadoRepository;
import com.dautofreitas.votacaoexecicio.infra.JpaInterfaces.AssociadoRepositoryJpa;
import com.dautofreitas.votacaoexecicio.infra.entity.EntityAssociado;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@AllArgsConstructor
@Repository
public class AssociadoRepositoryImp implements AssociadoRepository {
    private AssociadoRepositoryJpa jpa;
    @Override
    public Optional<Associado> buscarPorId(UUID associadoId) {


        Optional<EntityAssociado> associado = jpa.findById(associadoId);

        if(associado.isPresent())
        {
            return Optional.of(EntityAssociado.toDomain(associado.get()));
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public Associado salvar(Associado associado) {
        return EntityAssociado.toDomain(jpa.save(EntityAssociado.fromDomain(associado)));
    }

    @Override
    public List<Associado> buscarTodos() {
        return jpa.findAll().stream().map(EntityAssociado::toDomain).toList();
    }
}
