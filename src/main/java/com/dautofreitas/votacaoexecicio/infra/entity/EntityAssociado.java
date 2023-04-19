package com.dautofreitas.votacaoexecicio.infra.entity;

import com.dautofreitas.votacaoexecicio.domain.entity.Associado;
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
@Entity(name="ASSOCIADO")
public class EntityAssociado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;

    public static EntityAssociado fromDomain( Associado domain)
    {
        return new EntityAssociado(domain.getId(), domain.getNome()) ;
    }

    public static Associado toDomain( EntityAssociado entity)
    {
        return new Associado(entity.getId(), entity.getNome());
    }
}
