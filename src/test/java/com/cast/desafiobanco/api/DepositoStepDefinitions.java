package com.cast.desafiobanco.api;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class DepositoStepDefinitions {
    @Dado("que existam as seguintes contas")
    public void que_existam_as_seguintes_contas(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    @Dado("que seja solicitado um depósito de {string}")
    public void que_seja_solicitado_um_depósito_de(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Quando("for executada a operação de depósito")
    public void for_executada_a_operação_de_depósito() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Então("deverá ser apresentada a seguinte mensagem {string}")
    public void deverá_ser_apresentada_a_seguinte_mensagem(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Então("o saldo da conta {string} deverá ser de {string}")
    public void o_saldo_da_conta_deverá_ser_de(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
