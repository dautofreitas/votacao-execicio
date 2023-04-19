package com.dautofreitas.votacaoexecicio.application.dtos;

import com.dautofreitas.votacaoexecicio.domain.entity.Associado;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@AllArgsConstructor
@Data
public class AssociadoDto {
     private UUID id;

    private String nome;

    public static AssociadoDto fromDomain( Associado domain)
    {
        return new AssociadoDto(domain.getId(), domain.getNome()) ;
    }

    public static Associado toDomain( AssociadoDto dto)
    {
        return new Associado(dto.getId(), dto.getNome());
    }
}
