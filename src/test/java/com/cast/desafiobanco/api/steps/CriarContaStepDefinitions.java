package com.cast.desafiobanco.api.steps;

import com.cast.desafiobanco.api.dto.ContaDto;
import com.cast.desafiobanco.api.dto.ResponseDto;
import com.cast.desafiobanco.api.exception.Exception;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.List;

import java.util.Objects;
import java.util.logging.Logger;


public class CriarContaStepDefinitions {

    private static final Logger log = Logger.getLogger(CriarContaStepDefinitions.class.getName());

    public String url = "http://localhost:8081";
    private ContaDto contaDto = new ContaDto();
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseDto response = new ResponseDto();

    @Dado("^que seja solicitada a criação de uma nova conta com os seguintes valores$")
    public void que_seja_solicitada_a_criação_de_uma_nova_conta_com_os_seguintes_valores(DataTable dataTable) throws IOException {
        List<String> list = dataTable.asList();
        contaDto.setSaldo(Double.parseDouble(list.get(5)));
        contaDto.setNome(list.get(3));
        contaDto.setCpf(list.get(4));
        log.info(contaDto.toString());
    }
    @Quando("^for enviada a solicitação de criação de nova conta$")
    public void for_enviada_a_solicitação_de_criação_de_nova_conta() {
        try {
            String urlCriarConta = url+"/contas";
            response = restTemplate.postForObject(urlCriarConta, contaDto, ResponseDto.class);
        }catch (HttpClientErrorException e) {
            log.info(e.getResponseBodyAsString());
        }
    }
    @Então("deverá ser apresentada a seguinte mensagem de erro {string}")
    public void deverá_ser_apresentada_a_seguinte_mensagem_de_erro(String mensagemDeErro) {
        log.info(response.getMensagem());
        Assert.assertEquals(mensagemDeErro, response.getErro().getTitulo());
    }

    @Então("deverá ser apresentada a seguinte mensagem {string}")
    public void deverá_ser_apresentada_a_seguinte_mensagem(String mensagem) {
        log.info(response.getMensagem());
        Assert.assertEquals(mensagem, response.getMensagem());
    }

    @Então("^deverá ser retornado o número da conta criada$")
    public void deverá_ser_retornado_o_número_da_conta_criada() {
        log.info(response.getCorpo().get(0).getId().toString());
        Assert.assertNotNull(response.getCorpo().get(0).getId());
    }
}
