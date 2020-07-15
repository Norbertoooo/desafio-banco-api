package com.cast.desafiobanco.api.service.Impl;

import com.cast.desafiobanco.api.domain.Conta;
import com.cast.desafiobanco.api.repository.ContaRepository;
import com.cast.desafiobanco.api.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public List<Conta> findAll() {
        return contaRepository.findAll();
    }

    @Override
    public Conta create(Conta conta) {
        return contaRepository.save(conta);
    }

    @Override
    public Optional<Conta> findById(Long id) {
        return contaRepository.findById(id);
    }

    public boolean vereficadorDeLimiteAoCriarConta(Conta conta) {
        Double limite = 50.00;
        return conta.getSaldo() >= limite;
    }

    public boolean vereficadorDeLimiteDeDeposito(Double valor) {
        Double limite = 500.00;
        return valor >= limite;
    }

    public Conta depositar(Long id, Double valor) {
        Optional<Conta> conta = findById(id);
        if (conta.isPresent()) {
            conta.get().setSaldo( conta.get().getSaldo() + valor);
        }
        return contaRepository.save(conta.get());
    }

    public Conta sacar(Long id, Double valor) {
        Optional<Conta> conta = findById(id);
        if (conta.isPresent()) {
            conta.get().setSaldo( conta.get().getSaldo() - valor);
        }
        return contaRepository.save(conta.get());
    }

    public List<Conta> depositarEntreContas(Conta contaSolicitante, Conta contaBeneficiario, Double valor) {
        contaSolicitante.setSaldo(contaSolicitante.getSaldo() - valor);
        contaBeneficiario.setSaldo(contaBeneficiario.getSaldo() + valor);
        contaRepository.save(contaSolicitante);
        contaRepository.save(contaBeneficiario);
        return Collections.emptyList();
    }
}
