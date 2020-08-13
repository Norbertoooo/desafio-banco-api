package com.cast.desafiobanco.api.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import lombok.extern.slf4j.Slf4j;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
public class SaqueStepDefinitions extends StepDefs {

    private Double valorDeSaque;
    
    @Autowired
    private MockMvc mockMvc;

    @Dado("que seja solicitado um saque de {string}")
    public void que_seja_solicitado_um_saque_de(String saque) {
        valorDeSaque = Double.parseDouble(saque);
        log.info("Valor de saque: {}", saque);
    }

    @Quando("for executada a operação de saque")
    public void for_executada_a_operação_de_saque() throws Exception {
        // TODO: 12/08/2020 virar chamada direta

        Long numeroDaConta = conta.getNumeroConta();

        log.info(numeroDaConta.toString());

        actions = this.mockMvc
                .perform(put("/contas/saques/" + numeroDaConta + "/"+ valorDeSaque)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print()); 
    }

}
