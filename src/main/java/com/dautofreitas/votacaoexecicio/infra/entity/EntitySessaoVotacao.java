package com.dautofreitas.votacaoexecicio.infra.entity;

import com.dautofreitas.votacaoexecicio.domain.entity.SessaoVotacao;
import com.dautofreitas.votacaoexecicio.domain.enums.SessaoVotacaoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name ="SESSAO_VOTACAO")
public class EntitySessaoVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    private EntityPauta pauta;
    @OneToMany(mappedBy="sessaoVotacao")
    private List<EntityVoto> votos;
    private SessaoVotacaoStatus status;
    private LocalDateTime momentoInicio;
    private int tempoVotacaoInMinutes;

    public static  EntitySessaoVotacao fromDomain(SessaoVotacao domain)
    {
        return new EntitySessaoVotacao(domain.getId(),
                EntityPauta.fromDomain(domain.getPauta()),
                domain.getVotos().stream().map(EntityVoto::fromDomain).toList(),
                domain.getStatus(),
                domain.getMomentoInicio(),
                domain.getTempoVotacaoEmSegundos());

    }

    public static  SessaoVotacao toDomain(EntitySessaoVotacao entity)
    {
        return new SessaoVotacao(entity.getId(),
                EntityPauta.toDomain(entity.getPauta()),
                entity.getVotos().stream().map(EntityVoto::toDomain).toList(),
                entity.getStatus(),
                entity.getMomentoInicio(),
                entity.getTempoVotacaoInMinutes()
                );
    }
}
