package com.dautofreitas.votacaoexecicio.infra.repository;

import com.dautofreitas.votacaoexecicio.domain.entity.Pauta;
import com.dautofreitas.votacaoexecicio.domain.interfaces.repository.PautaRepository;
import com.dautofreitas.votacaoexecicio.infra.JpaInterfaces.PautaRepositoryJpa;
import com.dautofreitas.votacaoexecicio.infra.entity.EntityPauta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@AllArgsConstructor
@Repository
public class PautaRepositoryImp implements PautaRepository {
    private PautaRepositoryJpa jpa;
    @Override
    public Optional<Pauta> buscarPorId(UUID pautaId) {

        Optional<EntityPauta> pauta = jpa.findById(pautaId);

        if(pauta.isPresent())
        {
            return Optional.of(EntityPauta.toDomain(pauta.get()));
        }
        else{
            return Optional.empty();
        }

    }

    @Override
    public Pauta salvar(Pauta pauta) {
        return EntityPauta.toDomain(jpa.save(EntityPauta.fromDomain(pauta)));
    }

    @Override
    public List<Pauta> buscarTodos() {
        return jpa.findAll().stream().map(EntityPauta::toDomain).toList();
    }
}
