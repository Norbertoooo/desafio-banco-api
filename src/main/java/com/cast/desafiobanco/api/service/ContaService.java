package com.cast.desafiobanco.api.service;

import com.cast.desafiobanco.api.domain.Conta;

import java.util.List;

public interface ContaService {
    List<Conta> findAll();
    Conta create(Conta conta);
    Conta findById(Long id);
    void deleteById(Long id);
    Conta depositar(Long id, Double valor);
    Conta sacar(Long id, Double valor);
    boolean vereficadorDeLimite(Double valor);
    boolean vereficardoDeSaldoNaConta(Conta conta, Double valor);
    boolean vereficadorDeLimiteAoCriarConta(Conta conta);
    List<Conta> transferir(Conta contaSolicitante, Conta ContaBeneficiario, Double valor);

}
