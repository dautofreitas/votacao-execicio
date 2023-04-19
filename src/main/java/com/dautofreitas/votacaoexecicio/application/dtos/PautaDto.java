package com.dautofreitas.votacaoexecicio.application.dtos;

import com.dautofreitas.votacaoexecicio.domain.entity.Pauta;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@AllArgsConstructor
@Data
public class PautaDto {

    private UUID id;
    private String nome;
    private String descricao;

    public static  PautaDto fromDomain( Pauta domain)
    {
        return new PautaDto(domain.getId(), domain.getNome(), domain.getDescricao()) ;
    }

    public static  Pauta toDomain( PautaDto dto)
    {
        return new Pauta(dto.getId(), dto.getNome(), dto.getDescricao());
    }
}
