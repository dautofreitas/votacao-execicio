package com.dautofreitas.votacaoexecicio.application.dtos;

import com.dautofreitas.votacaoexecicio.domain.entity.SessaoVotacao;
import com.dautofreitas.votacaoexecicio.domain.enums.SessaoVotacaoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@Data
public class SessaoVotacaoDto {

    private UUID id;

    private PautaDto pauta;

    private List<VotoDto> votos;
    private SessaoVotacaoStatus status;
    private LocalDateTime momentoInicio;
    private int tempoVotacaoInMinutes;

    public static  SessaoVotacaoDto fromDomain(SessaoVotacao domain)
    {
        return new SessaoVotacaoDto(domain.getId(),
                PautaDto.fromDomain(domain.getPauta()),
                domain.getVotos().stream().map(VotoDto::fromDomain).toList(),
                domain.getStatus(),
                domain.getMomentoInicio(),
                domain.getTempoVotacaoEmSegundos());

    }

    public static  SessaoVotacao toDomain(SessaoVotacaoDto dto)
    {
        return new SessaoVotacao(dto.getId(),
                PautaDto.toDomain(dto.getPauta()),
                dto.getVotos().stream().map(VotoDto::toDomain).toList(),
                dto.getStatus(),
                dto.getMomentoInicio(),
                dto.getTempoVotacaoInMinutes()
                );
    }
}
