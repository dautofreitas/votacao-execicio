package com.dautofreitas.votacaoexecicio.infra.entity;

import com.dautofreitas.votacaoexecicio.domain.entity.Pauta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="PAUTA")
public class EntityPauta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String descricao;

    public static  EntityPauta fromDomain( Pauta domain)
    {
        return new EntityPauta(domain.getId(), domain.getNome(), domain.getDescricao()) ;
    }

    public static  Pauta toDomain( EntityPauta entity)
    {
        return new Pauta(entity.getId(), entity.getNome(), entity.getDescricao());
    }
}
