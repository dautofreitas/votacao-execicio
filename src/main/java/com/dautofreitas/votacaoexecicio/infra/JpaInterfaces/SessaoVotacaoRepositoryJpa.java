package com.dautofreitas.votacaoexecicio.infra.JpaInterfaces;
import com.dautofreitas.votacaoexecicio.infra.entity.EntitySessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface SessaoVotacaoRepositoryJpa  extends JpaRepository<EntitySessaoVotacao, UUID> {


    @Query(value = "SELECT s FROM SESSAO_VOTACAO s JOIN s.pauta p WHERE p.id =?1")
    Optional<EntitySessaoVotacao> buscarSessaoVotacaoAbertaByPautaId(UUID pautaId);
}
