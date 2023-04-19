package com.dautofreitas.votacaoexecicio.rest;

import com.dautofreitas.votacaoexecicio.application.dtos.AssociadoDto;
import com.dautofreitas.votacaoexecicio.application.dtos.PautaDto;
import com.dautofreitas.votacaoexecicio.application.dtos.SessaoVotacaoDto;
import com.dautofreitas.votacaoexecicio.domain.enums.OpcaoVoto;
import com.dautofreitas.votacaoexecicio.domain.service.SessaoVotacaoServiceImp;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.dautofreitas.votacaoexecicio.util.RestAssuredUtil.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SessaoVotacaoApiTest {
    @LocalServerPort
    private int port;
    @BeforeEach
    public void setup(){
        RestAssured.reset();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

    }
    @Test
    public void deveAbrirVotacao()
    {
        PautaDto pauta = new PautaDto(UUID.randomUUID(), "test", "test");

        PautaDto pautaCriada = criaPauta(pauta).then().extract().as(PautaDto.class);

        abrirVotacao(pautaCriada.getId(),
                SessaoVotacaoServiceImp.tempoVotacaoEmSegundosDefault)
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }



    @Test
    public void deveVotar()
    {
        PautaDto pauta = new PautaDto(UUID.randomUUID(), "test", "test");
        AssociadoDto associado = new AssociadoDto(UUID.randomUUID(), "test");

        PautaDto pautaCriada = criaPauta(pauta).then().extract().as(PautaDto.class);
        AssociadoDto associadoCriado = criaAssociado(associado).then().extract().as(AssociadoDto.class);

        abrirVotacao(pautaCriada.getId(),
                SessaoVotacaoServiceImp.tempoVotacaoEmSegundosDefault)
                .then()
                .statusCode(200)
                .body("id", notNullValue());

        votar(pautaCriada.getId(), associadoCriado.getId(), OpcaoVoto.SIM)
                .then()
                .statusCode(200);
    }

    @Test
    public void deveVotarRetonarResultadoVotacao()
    {


        PautaDto pauta = criaPauta(new PautaDto(UUID.randomUUID(), "test", "test"))
                .then().extract().as(PautaDto.class);
        AssociadoDto associado1 = criaAssociado(new AssociadoDto(UUID.randomUUID(), "test"))
                .then().extract().as(AssociadoDto.class);
        AssociadoDto associado2 = criaAssociado(new AssociadoDto(UUID.randomUUID(), "test"))
                .then().extract().as(AssociadoDto.class);


        SessaoVotacaoDto sessao = abrirVotacao(pauta.getId(),
                SessaoVotacaoServiceImp.tempoVotacaoEmSegundosDefault).then().extract().as(SessaoVotacaoDto.class);

        votar(pauta.getId(), associado1.getId(), OpcaoVoto.SIM)
                .then()
                .statusCode(200);

        votar(pauta.getId(), associado2.getId(), OpcaoVoto.NAO)
                .then()
                .statusCode(200);

        buscarResultado(sessao.getId()).then().statusCode(200)
                .body("totalVotosPositivos", equalTo(1))
                .body("totalVotosNegativos", equalTo(1));
    }

    @Test
    public void deveFalharQuandoAssociadoJavotou()    {


        PautaDto pauta = criaPauta(new PautaDto(UUID.randomUUID(), "test", "test"))
                .then().extract().as(PautaDto.class);
        AssociadoDto associado= criaAssociado(new AssociadoDto(UUID.randomUUID(), "test"))
                .then().extract().as(AssociadoDto.class);;



        abrirVotacao(pauta.getId(), SessaoVotacaoServiceImp.tempoVotacaoEmSegundosDefault)
                .then().extract().as(SessaoVotacaoDto.class);

        votar(pauta.getId(), associado.getId(), OpcaoVoto.SIM)
                .then()
                .statusCode(200);

        votar(pauta.getId(), associado.getId(), OpcaoVoto.SIM)
                .then()
                .statusCode(400)
                .body("mensagem", equalTo("Associado já possui voto na pauta"));


    }

    @Test
    public void deveFalharQuandoQuandoOtempoParaVotacaoJaExpirou() throws InterruptedException {


        PautaDto pauta = criaPauta(new PautaDto(UUID.randomUUID(), "test", "test"))
                .then().extract().as(PautaDto.class);
        AssociadoDto associado= criaAssociado(new AssociadoDto(UUID.randomUUID(), "test"))
                .then().extract().as(AssociadoDto.class);

        abrirVotacao(pauta.getId(), 2).then().extract().as(SessaoVotacaoDto.class);

        TimeUnit.SECONDS.sleep(3);

        votar(pauta.getId(), associado.getId(), OpcaoVoto.SIM)
                .then()
                .statusCode(400);


    }
}
