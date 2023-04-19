package com.dautofreitas.votacaoexecicio.rest;

import com.dautofreitas.votacaoexecicio.application.dtos.AssociadoDto;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;

import static com.dautofreitas.votacaoexecicio.util.RestAssuredUtil.criaAssociado;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AssociadoApiTest {
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
        AssociadoDto associado = new AssociadoDto(UUID.randomUUID(), "test");
        criaAssociado(associado)
                .then()
                .statusCode(201);
    }

    @Test
    public void deeveRetornarAssociadoPorId()
    {
        AssociadoDto associado = new AssociadoDto(UUID.randomUUID(), "test");

        AssociadoDto associadoCreated =  criaAssociado(associado)
                .then()
                .statusCode(201)
                .extract().as(AssociadoDto.class);


        RestAssured.with()
            .pathParam("associadoId", associadoCreated.getId())
            .when()
            .get("/v1/associado/{associadoId}")
            .then()
            .statusCode(200)
            .body("id", equalTo(associadoCreated.getId().toString()))
            .body("nome", equalTo(associadoCreated.getNome()));


    }

    @Test
    public void deeveRetornarTodos()
    {
        AssociadoDto associado1 = new AssociadoDto(UUID.randomUUID(), "test1");
        AssociadoDto associado2 = new AssociadoDto(UUID.randomUUID(), "test2");

        criaAssociado(associado1);
        criaAssociado(associado2);

        AssociadoDto[] associados =  RestAssured.with()
                .when()
                .get("/v1/associado/")
                .then()
                .statusCode(200)
                .extract().as(AssociadoDto[].class);

        assertThat(associados.length).isEqualTo(2);


    }

}
