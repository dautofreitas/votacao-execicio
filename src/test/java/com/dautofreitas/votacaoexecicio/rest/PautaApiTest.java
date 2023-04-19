package com.dautofreitas.votacaoexecicio.rest;

import com.dautofreitas.votacaoexecicio.application.dtos.AssociadoDto;
import com.dautofreitas.votacaoexecicio.application.dtos.PautaDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;

import static com.dautofreitas.votacaoexecicio.util.RestAssuredUtil.criaPauta;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PautaApiTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup(){
        RestAssured.reset();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

    }

    @Test
    public void deveCriarAsociado()
    {
        PautaDto pauta = new PautaDto(UUID.randomUUID(), "test", "test");
        criaPauta(pauta)
                .then()
                .statusCode(201);
    }

    @Test
    public void deveRetornarPautaPorId()
    {
        PautaDto pauta = new PautaDto(UUID.randomUUID(), "test", "test");

        PautaDto pautaCriada =  criaPauta(pauta)
                .then()
                .statusCode(201)
                .extract().as(PautaDto.class);


        RestAssured.with()
                .pathParam("pautaId", pautaCriada.getId())
                .when()
                .get("/v1/pauta/{pautaId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(pautaCriada.getId().toString()))
                .body("nome", equalTo(pautaCriada.getNome()))
                .body("descricao", equalTo(pautaCriada.getDescricao()));


    }

    @Test
    public void deeveRetornarTodos()
    {
        PautaDto pauta1 = new PautaDto(UUID.randomUUID(), "test1", "test1");
        PautaDto pauta2 = new PautaDto(UUID.randomUUID(), "test2", "test1");

        criaPauta(pauta1);
        criaPauta(pauta2);

        PautaDto[] pautas =  RestAssured.with()
                .when()
                .get("/v1/pauta")
                .then()
                .statusCode(200)
                .extract().as(PautaDto[].class);

        assertThat(pautas.length).isEqualTo(2);


    }
}
