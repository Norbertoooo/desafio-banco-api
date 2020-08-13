package com.cast.desafiobanco.api.steps;

import com.cast.desafiobanco.api.domain.Conta;
import com.cast.desafiobanco.api.service.ContaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
public class DepositoStepDefinitions extends StepDefs{

    private Double valorDeDeposito;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContaService contaService;

    @Dado("que existam as seguintes contas")
    public void que_existam_as_seguintes_contas(List<Conta> contas) throws Exception {
        // TODO: 12/08/2020 virar contexto

        conta = contas.stream().findFirst().get();

        log.info(conta.toString());

        Conta contaRetornadaDoService = contaService.create(conta);

        log.info("Conta retornada do service: " + contaRetornadaDoService);
    }

    @Dado("que seja solicitado um depósito de {string}")
    public void que_seja_solicitado_um_depósito_de(String deposito) {
        valorDeDeposito = Double.parseDouble(deposito);
        log.info(valorDeDeposito.toString());
    }

    @Quando("for executada a operação de depósito")
    public void for_executada_a_operação_de_depósito() throws Exception {
        // TODO: 12/08/2020 virar chamada direta
        actions = this.mockMvc
                .perform(put("/contas/depositos/" + conta.getNumeroConta() + "/" + valorDeDeposito)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
    }

    @Então("o saldo da conta {string} deverá ser de {string}")
    public void o_saldo_da_conta_deverá_ser_de(String numeroDaConta, String saldo) {
        // TODO: 12/08/2020  virar verificador
        Conta conta = contaService.findByNumeroConta(Long.valueOf(numeroDaConta));
        assert conta.getSaldo() == Double.parseDouble(saldo);
    }
}
