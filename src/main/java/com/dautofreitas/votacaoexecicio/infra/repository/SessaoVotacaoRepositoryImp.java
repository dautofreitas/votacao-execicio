package com.dautofreitas.votacaoexecicio.infra.repository;

import com.dautofreitas.votacaoexecicio.domain.entity.SessaoVotacao;
import com.dautofreitas.votacaoexecicio.domain.entity.Voto;
import com.dautofreitas.votacaoexecicio.domain.enums.SessaoVotacaoStatus;
import com.dautofreitas.votacaoexecicio.domain.interfaces.repository.SessaoVotacaoRepository;
import com.dautofreitas.votacaoexecicio.infra.JpaInterfaces.SessaoVotacaoRepositoryJpa;
import com.dautofreitas.votacaoexecicio.infra.entity.EntitySessaoVotacao;
import com.dautofreitas.votacaoexecicio.infra.entity.EntityVoto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class SessaoVotacaoRepositoryImp implements SessaoVotacaoRepository {
    private SessaoVotacaoRepositoryJpa jpa;
    @Override
    public SessaoVotacao salvar(SessaoVotacao sessaoVotacao) {
        return EntitySessaoVotacao.toDomain(
                jpa.save(
                        EntitySessaoVotacao.fromDomain(sessaoVotacao)
                )
        );
    }

    @Override
    public Optional<SessaoVotacao> buscarSessaoVotacaoAbertaPorPautaId(UUID pautaId) {

        Optional<EntitySessaoVotacao> sessaoVotacao =jpa.buscarSessaoVotacaoAbertaByPautaId(pautaId);
        if(sessaoVotacao.isPresent())
        {
            return Optional.of(EntitySessaoVotacao.toDomain(sessaoVotacao.get()));
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<SessaoVotacao> buscarSessaoVotacaoPorId(UUID sessaoId) {
        Optional<EntitySessaoVotacao> sessaoVotacao = jpa.findById(sessaoId);
        if(sessaoVotacao.isPresent())
        {
            return Optional.of(EntitySessaoVotacao.toDomain(sessaoVotacao.get()));
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public List<SessaoVotacao> buscarTodos() {
        return jpa.findAll().stream().map(EntitySessaoVotacao::toDomain).toList();
    }

}
