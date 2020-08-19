package com.cast.desafiobanco.api.shared;

public abstract class Constantes {

    // Mensagens de sucesso
    public final String MENSAGEM_DE_SUCESSO_AO_CRIAR_CONTA = "Conta cadastrada com sucesso!";
    public final String MENSAGEM_DE_SUCESSO_AO_DEPOSITAR = "Depósito realizado com sucesso!";
    public final String MENSAGEM_DE_SUCESSO_AO_SACAR = "Saque realizado com sucesso!";
    public final String MENSAGEM_DE_SUCESSO_AO_TRANSFERIR = "Transferência realizada com sucesso!";
    public final String MENSAGEM_DE_SUCESSO_AO_EXCLUIR = "Conta excluida com sucesso!";

    // Mensagens de erro
    public final String SALDO_INSUFICIENTE_PARA_ABERTURA_DE_CONTA = "Saldo insuficiente para abertura de nova conta.";
    public final String VALOR_MAXIMO_DE_OPERACAO = "Operação de transferência tem um limite máximo de 500 por operação.";
    public final String SALDO_INSUFICIENTE = "Saldo insuficiente para a operação.";
    public final String CONTA_NAO_EXISTENTE = "Número de conta não existente";

    // Valores
    public final Double VALOR_MINIMO_PARA_CRIAR_CONTA = 50.00;
    public final Double VALOR_MAXIMO_PARA_TRANSFERENCIA = 500.00;
}
