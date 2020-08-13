package com.cast.desafiobanco.api.steps;

import com.cast.desafiobanco.api.domain.Conta;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

public abstract class StepDefs {

    protected static ResultActions actions;

    protected static Conta conta;

    protected static List<Conta> listaDeContas = new ArrayList<>();

    // TODO: 12/08/2020 adicionar lista de contas de contexto

}
