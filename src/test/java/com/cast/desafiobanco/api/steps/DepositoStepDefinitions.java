package com.cast.desafiobanco.api.steps;

import com.cast.desafiobanco.api.domain.Conta;
import com.cast.desafiobanco.api.service.ContaService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
public class DepositoStepDefinitions extends StepDefs{

    private Double valorDeDeposito;

    @Autowired
    private ContaService contaService;

    @Dado("que existam as seguintes contas")
    public void que_existam_as_seguintes_contas(List<Conta> contas) throws Exception {
        // TODO: 12/08/2020 virar contexto
        log.info("Quantidade de Contas encontradas: {}", contas.size());

        contas.forEach(conta -> log.info(conta.toString()));

        contas.forEach(contaParaSalvar ->
                listaDeContas.add(contaService.create(contaParaSalvar))
        );
    }

    @Dado("que seja solicitado um depósito de {string}")
    public void que_seja_solicitado_um_depósito_de(String deposito) {
        valorDeDeposito = Double.parseDouble(deposito);
        log.info(valorDeDeposito.toString());
    }

    @Quando("for executada a operação de depósito")
    public void for_executada_a_operação_de_depósito() throws Exception {
        // TODO: 12/08/2020 virar chamada direta

        Long numeroDaConta = listaDeContas.stream().findFirst().get().getNumeroConta();

        actions = this.mockMvc
                .perform(put("/contas/depositos/" + numeroDaConta + "/" + valorDeDeposito)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Então("o saldo da conta {string} deverá ser de {string}")
    public void o_saldo_da_conta_deverá_ser_de(String numeroDaConta, String saldo) {
        // TODO: 12/08/2020  virar verificador
        Conta conta = contaService.findByNumeroConta(Long.valueOf(numeroDaConta));
        assert conta.getSaldo() == Double.parseDouble(saldo);
    }
}
