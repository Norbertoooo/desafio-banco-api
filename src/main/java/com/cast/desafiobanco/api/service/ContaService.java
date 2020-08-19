package com.cast.desafiobanco.api.service;

import com.cast.desafiobanco.api.domain.Conta;

import java.util.List;

public interface ContaService {
    List<Conta> findAll();
    Conta create(Conta conta);
    Conta findById(Long id);
    Conta findByNumeroConta(Long numeroConta);
    void deleteById(Long id);
    Conta depositar(Conta conta, Double valor);
    Conta sacar(Conta conta, Double valor);
    Long gerarNumeroDaConta();
    boolean vereficarLimiteMinimo(Double valor);
    boolean vereficarSaldoNaConta(Conta conta, Double valor);
    boolean vereficarLimiteAoCriarConta(Conta conta);
    List<Conta> transferir(Conta contaSolicitante, Conta ContaBeneficiario, Double valor);

}
