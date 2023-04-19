package com.dautofreitas.votacaoexecicio.infra.repository;

import com.dautofreitas.votacaoexecicio.domain.entity.Pauta;
import com.dautofreitas.votacaoexecicio.domain.entity.SessaoVotacao;
import com.dautofreitas.votacaoexecicio.domain.entity.Voto;
import com.dautofreitas.votacaoexecicio.domain.interfaces.repository.PautaRepository;
import com.dautofreitas.votacaoexecicio.domain.interfaces.repository.VotoRepository;
import com.dautofreitas.votacaoexecicio.infra.JpaInterfaces.PautaRepositoryJpa;
import com.dautofreitas.votacaoexecicio.infra.JpaInterfaces.VotoRepositoryJpa;
import com.dautofreitas.votacaoexecicio.infra.entity.EntityPauta;
import com.dautofreitas.votacaoexecicio.infra.entity.EntitySessaoVotacao;
import com.dautofreitas.votacaoexecicio.infra.entity.EntityVoto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class VotoRepositoryImp implements VotoRepository {
    private VotoRepositoryJpa jpa;

    @Override
    public void salvar(Voto voto, SessaoVotacao sessaoVotacao) {
        EntityVoto entity  = EntityVoto.fromDomain(voto);
        entity.setSessaoVotacao(EntitySessaoVotacao.fromDomain(sessaoVotacao));

        jpa.save(entity);
    }
}
