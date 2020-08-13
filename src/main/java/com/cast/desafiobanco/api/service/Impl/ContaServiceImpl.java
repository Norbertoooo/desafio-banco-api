package com.cast.desafiobanco.api.service.Impl;

import com.cast.desafiobanco.api.domain.Conta;
import com.cast.desafiobanco.api.exception.DomainException;
import com.cast.desafiobanco.api.repository.ContaRepository;
import com.cast.desafiobanco.api.service.ContaService;
import com.cast.desafiobanco.api.shared.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class ContaServiceImpl extends Constantes implements ContaService {

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
    public Conta findById(Long id) {
        return contaRepository.findById(id).
                orElseThrow( () -> new DomainException(CONTA_NAO_EXISTENTE));
    }

    @Override
    public Conta findByNumeroConta(Long numeroConta) {
        return contaRepository.findByNumeroConta(numeroConta)
                .orElseThrow( () -> new DomainException(CONTA_NAO_EXISTENTE));
    }

    @Override
    public void deleteById(Long id) {
        contaRepository.deleteById(id);
    }

    @Override
    public boolean vereficadorDeLimiteAoCriarConta(Conta conta) {
        if(conta.getSaldo() >= VALOR_MINIMO_PARA_CRIAR_CONTA) {
            return true;
        }else{
            throw new DomainException(SALDO_INSUFICIENTE_PARA_ABERTURA_DE_CONTA);
        }
    }

    @Override
    public boolean vereficadorDeLimite(Double valor) {
        if(valor <= VALOR_MAXIMO_PARA_TRANSFERENCIA) {
            return true;
        }else {
            throw new DomainException(VALOR_MAXIMO_DE_OPERACAO);
        }
    }

    @Override
    public boolean vereficardoDeSaldoNaConta(Conta conta, Double valor) {
        if (valor <= conta.getSaldo()) {
            return true;
        }else{
            throw new DomainException(SALDO_INSUFICIENTE);
        }
    }

    @Override
    public Conta depositar(Long numeroDaConta, Double valor) {
      Conta conta = this.findByNumeroConta(numeroDaConta);
      conta.setSaldo( conta.getSaldo() + valor);
      return contaRepository.save(conta);
    }

    @Override
    public Conta sacar(Long numeroDaConta, Double valor) {
        Conta conta = this.findByNumeroConta(numeroDaConta);
        conta.setSaldo( conta.getSaldo() - valor);
        return contaRepository.save(conta);
    }

    @Override
    public Long geradorDeNumeroDaConta() {
        Random random = new Random();
        long numeroDaConta = random.nextInt(100000);
        return numeroDaConta;
    }

    @Override
    public List<Conta> transferir(Conta contaSolicitante, Conta contaBeneficiario, Double valor) {
        List<Conta> listaDeContas = new ArrayList<>();
        contaSolicitante.setSaldo(contaSolicitante.getSaldo() - valor);
        contaBeneficiario.setSaldo(contaBeneficiario.getSaldo() + valor);
        listaDeContas.add(contaSolicitante);
        listaDeContas.add(contaBeneficiario);
        contaRepository.saveAll(listaDeContas);
        return listaDeContas;
    }
}
