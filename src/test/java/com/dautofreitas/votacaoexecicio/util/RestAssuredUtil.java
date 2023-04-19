package com.dautofreitas.votacaoexecicio.util;

import com.dautofreitas.votacaoexecicio.application.dtos.AssociadoDto;
import com.dautofreitas.votacaoexecicio.application.dtos.PautaDto;
import com.dautofreitas.votacaoexecicio.domain.enums.OpcaoVoto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.UUID;

public class RestAssuredUtil {
    public static Response criaAssociado(AssociadoDto associado) {
        return RestAssured.with()
                .body(associado)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .post("/v1/associado");
    }

    public static Response criaPauta(PautaDto pauta) {
        return RestAssured.with()
                .body(pauta)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .post("/v1/pauta");
    }

    public static Response abrirVotacao(UUID pautaId, int tempoVotacaoEmSegundos) {
        return RestAssured.with()
                .queryParam("pautaId", pautaId)
                .queryParam("tempo", tempoVotacaoEmSegundos)
                .when()
                .get("/v1/sessao/abrir-votacao");
    }

    public static Response votar(UUID pautaId, UUID associadoId, OpcaoVoto opcao) {
        return RestAssured.with()
                .queryParam("pautaId", pautaId)
                .queryParam("associadoId", associadoId)
                .queryParam("opcao", opcao)
                .when()
                .get("/v1/sessao/votar");
    }
    public static Response buscarResultado(UUID sessaoId) {
        return RestAssured.with()
                .queryParam("sessaoId", sessaoId)
                .when()
                .get("/v1/sessao/resultado");
    }
}
