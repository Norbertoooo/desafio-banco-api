package com.cast.desafiobanco.api.steps;

import com.cast.desafiobanco.api.dto.TransferenciaDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class TransferenciaStepDefinitions extends StepDefs {

    TransferenciaDto transferenciaDto = new TransferenciaDto();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Dado("que seja solicitada um transferência com as seguintes informações")
    public void que_seja_solicitada_um_transferência_com_as_seguintes_informações(List<TransferenciaDto> listaDeTransferencia) {
        transferenciaDto = listaDeTransferencia.stream().findFirst().get();
        log.info(transferenciaDto.toString());
    }

    @Quando("for executada a operação de transferência")
    public void for_executada_a_operação_de_transferência() throws Exception {
        // TODO: 12/08/2020 virar chamada direta
        actions = mockMvc.perform(put("/contas/transferencias")
                    .content(objectMapper.writeValueAsString(transferenciaDto))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
    }

}
