package com.cast.desafiobanco.api.steps;

import com.cast.desafiobanco.api.domain.Conta;
import com.cast.desafiobanco.api.dto.TransferenciaDto;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.datatable.DataTable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.logging.Logger;

public class TransferenciaStepDefinitions {

    private static final Logger log = Logger.getLogger(TransferenciaStepDefinitions.class.getName());

    private TransferenciaDto transferenciaDto = new TransferenciaDto();

    @Dado("que seja solicitada um transferência com as seguintes informações")
    public void que_seja_solicitada_um_transferência_com_as_seguintes_informações(DataTable dataTable) {
        List<String> lista = dataTable.asList();
        transferenciaDto.setContaDoBeneficiario(Long.valueOf(lista.get(5)));
        transferenciaDto.setContaDoSolicitante(Long.valueOf(lista.get(3)));
        transferenciaDto.setValor(Double.parseDouble(lista.get(4)));
        log.info(lista.toString());
        log.info(transferenciaDto.toString());
    }

    @Quando("for executada a operação de transferência")
    public void for_executada_a_operação_de_transferência() {
        String urlParaTransferencia = "http://localhost:8081" + "/transferencias";

    }


}
