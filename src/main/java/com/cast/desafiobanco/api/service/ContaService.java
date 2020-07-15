package com.cast.desafiobanco.api.service;

import com.cast.desafiobanco.api.domain.Conta;

import java.util.List;
import java.util.Optional;

public interface ContaService {
    List<Conta> findAll();
    Conta create(Conta conta);
    Optional<Conta> findById(Long id);
    Conta depositar(Long id, Double valor);
    Conta sacar(Long id, Double valor);
    boolean vereficadorDeLimiteDeDeposito(Double valor);
    List<Conta> depositarEntreContas(Conta contaSolicitante, Conta ContaBeneficiario, Double valor);
    boolean vereficadorDeLimiteAoCriarConta(Conta conta);
}
