package com.cast.desafiobanco.api.shared;

public final class Constantes {

    // Mensagens de sucesso
    public static final String MENSAGEM_DE_SUCESSO_AO_CRIAR_CONTA = "Conta cadastrada com sucesso!";
    public static final String MENSAGEM_DE_SUCESSO_AO_DEPOSITAR = "Depósito realizado com sucesso!";
    public static final String MENSAGEM_DE_SUCESSO_AO_SACAR = "Saque realizado com sucesso!";
    public static final String MENSAGEM_DE_SUCESSO_AO_TRANSFERIR = "Transferência realizada com sucesso!";
    public static final String MENSAGEM_DE_SUCESSO_AO_EXCLUIR = "Conta excluida com sucesso!";

    // Mensagens de erro
    public static final String SALDO_INSUFICIENTE_PARA_ABERTURA_DE_CONTA = "Saldo insuficiente para abertura de nova conta.";
    public static final String VALOR_MAXIMO_DE_OPERACAO = "Operação de transferência tem um limite máximo de 500 por operação.";
    public static final String SALDO_INSUFICIENTE = "Saldo insuficiente para a operação.";
    public static final String CONTA_NAO_EXISTENTE = "Número de conta não existente";

    // Valores
    public static final Double VALOR_MINIMO_PARA_CRIAR_CONTA = 50.00;
    public static final Double VALOR_MAXIMO_PARA_TRANSFERENCIA = 500.00;
}
