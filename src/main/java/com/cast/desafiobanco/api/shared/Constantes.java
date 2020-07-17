package com.cast.desafiobanco.api.shared;

public final class Constantes {

    public Constantes() { }

    // Mensagens de sucesso
    public String MENSAGEM_DE_SUCESSO_AO_CRIAR_CONTA = "Conta cadastrada com sucesso!";
    public String MENSAGEM_DE_SUCESSO_AO_DEPOSITAR = "Depósito realizado com sucesso!";
    public String MENSAGEM_DE_SUCESSO_AO_SACAR = "Saque realizado com sucesso!";
    public String MENSAGEM_DE_SUCESSO_AO_TRANSFERIR = "Transferência realizada com sucesso!";
    public String MENSAGEM_DE_SUCESSO_AO_EXCLUIR = "Conta excluida com sucesso!";

    //Mensagens de erro
    public String SALDO_INSUFICIENTE_PARA_ABERTURA_DE_CONTA = "Saldo insuficiente para abertura de nova conta.";
    public String VALOR_MAXIMO_DE_OPERACAO = "Operação de transferência tem um limite máximo de 500 por operação.";
    public String SALDO_INSUFICIENTE = "Saldo insuficiente para a operação.";
    public String CONTA_NAO_EXISTENTE = "Número de conta não existente";

    // Valores
    public Double VALOR_MINIMO_PARA_CRIAR_CONTA = 50.00;
    public Double VALOR_MAXIMO_PARA_TRANSFERENCIA = 500.00;
}
