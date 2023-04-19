package com.dautofreitas.votacaoexecicio.application.dtos;

import com.dautofreitas.votacaoexecicio.domain.entity.ResultadoVotacao;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResultadoVotacaoDto {
    private long totalVotosPositivos;
    private long totalVotosNegativos;

    public static ResultadoVotacaoDto fromDomain(ResultadoVotacao domain)
    {
        return new ResultadoVotacaoDto(domain.getTotalVotosPositivos(),
                domain.getTotalVotosNegativos());
    }
}
