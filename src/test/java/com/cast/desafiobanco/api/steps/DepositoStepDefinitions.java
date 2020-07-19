package com.cast.desafiobanco.api.steps;

import com.cast.desafiobanco.api.domain.Conta;
import com.cast.desafiobanco.api.dto.ContaDto;
import com.cast.desafiobanco.api.dto.ResponseDto;
import io.cucumber.java.eo.Do;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

public class DepositoStepDefinitions {

    private static final Logger log = Logger.getLogger(DepositoStepDefinitions.class.getName());
    private Double valorDeDeposito;
    private CriarContaStepDefinitions criarConta = new CriarContaStepDefinitions();
    private final RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<ResponseDto> response;
    Conta conta = new Conta();

    @Dado("que existam as seguintes contas")
    public void que_existam_as_seguintes_contas(DataTable contas) {
        log.info(contas.asList().toString());

        List<String> listaDeContas = contas.asList();
        conta.setNome("deposito");
        conta.setCpf("213456789");
        conta.setId(Long.valueOf(listaDeContas.get(2)));
        conta.setSaldo(Double.parseDouble(listaDeContas.get(3)));
        log.info(conta.toString());
        String urlCriarConta = criarConta.url +"/contas";
        ResponseEntity responseEntity = restTemplate.postForEntity(urlCriarConta,conta,ResponseDto.class);
        log.info(responseEntity.getBody().toString());
    }

    @Dado("que seja solicitado um depósito de {string}")
    public void que_seja_solicitado_um_depósito_de(String deposito) {
        valorDeDeposito = Double.parseDouble(deposito);
        log.info(valorDeDeposito.toString());
    }

    @Quando("for executada a operação de depósito")
    public void for_executada_a_operação_de_depósito() {
        try {
            String urlParaOperacaoDeDeposito = criarConta.url + "/depositos/" + conta.getId() + "/" + valorDeDeposito;
            log.info(urlParaOperacaoDeDeposito);
            response = restTemplate.exchange(urlParaOperacaoDeDeposito, HttpMethod.PUT, response, ResponseDto.class);
        }catch (HttpClientErrorException ignored) { }
    }

    @Então("o saldo da conta {string} deverá ser de {string}")
    public void o_saldo_da_conta_deverá_ser_de(String conta, String saldo) {
        String urlParaConsulta = criarConta.url + "/contas/" + conta;
        Double saldoDouble = Double.parseDouble(saldo);
        ResponseEntity<Conta> responseEntity = restTemplate.getForEntity(urlParaConsulta, Conta.class);
        Assert.assertEquals(saldoDouble, responseEntity.getBody().getSaldo());
    }

}
