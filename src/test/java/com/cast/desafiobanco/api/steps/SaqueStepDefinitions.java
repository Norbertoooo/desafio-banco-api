package com.cast.desafiobanco.api.steps;

import com.cast.desafiobanco.api.dto.ResponseDto;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

public class SaqueStepDefinitions {

    private static final Logger log = Logger.getLogger(SaqueStepDefinitions.class.getName());
    private Double saque;
    private RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<ResponseDto> response;
    @Dado("que seja solicitado um saque de {string}")
    public void que_seja_solicitado_um_saque_de(String saque) {
        log.info(saque);
        this.saque = Double.parseDouble(saque);
        Assert.assertEquals(saque, this.saque.toString());
    }

    @Quando("for executada a operação de saque")
    public void for_executada_a_operação_de_saque() {
        try {
            String urlParaSaque = "http://localhost:8081" + "/saques";
            response = restTemplate.exchange(urlParaSaque, HttpMethod.PUT, null,ResponseDto.class);
            log.info(response.toString());
        }catch (HttpClientErrorException ignored) { }
    }

}
