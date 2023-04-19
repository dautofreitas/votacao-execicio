package com.dautofreitas.votacaoexecicio.infra.entity;

import com.dautofreitas.votacaoexecicio.domain.entity.Voto;
import com.dautofreitas.votacaoexecicio.domain.enums.OpcaoVoto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="VOTO")
public class EntityVoto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private OpcaoVoto opcao;
    @OneToOne
    private EntityAssociado associado;

    private LocalDateTime momentoVoto;
    @ManyToOne
    private EntitySessaoVotacao sessaoVotacao;
    public static  EntityVoto fromDomain(Voto domain)
    {
        return new EntityVoto(domain.getId(),
                domain.getOpcao(),
                EntityAssociado.fromDomain(domain.getAssociado()),
                domain.getMomentoVoto(), null);
    }
    public static Voto toDomain(EntityVoto entity)
    {
        return new Voto(entity.getId(),
                entity.getOpcao(),
                EntityAssociado.toDomain(entity.getAssociado()),
                entity.getMomentoVoto());
    }


}
