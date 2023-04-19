package com.dautofreitas.votacaoexecicio.application.dtos;

import com.dautofreitas.votacaoexecicio.domain.entity.Voto;
import com.dautofreitas.votacaoexecicio.domain.enums.OpcaoVoto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@Data
public class VotoDto {

    private UUID id;
    private OpcaoVoto opcao;

    private AssociadoDto associado;

    private LocalDateTime momentoVoto;

    public static  VotoDto fromDomain(Voto domain)
    {
        return new VotoDto(domain.getId(),
                domain.getOpcao(),
                AssociadoDto.fromDomain(domain.getAssociado()),
                domain.getMomentoVoto());
    }
    public static Voto toDomain(VotoDto dto)
    {
        return new Voto(dto.getId(),
                dto.getOpcao(),
                AssociadoDto.toDomain(dto.getAssociado()),
                dto.getMomentoVoto());
    }


}
