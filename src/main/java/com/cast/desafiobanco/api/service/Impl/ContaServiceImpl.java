package com.cast.desafiobanco.api.service.Impl;

import com.cast.desafiobanco.api.domain.Conta;
import com.cast.desafiobanco.api.exception.DomainException;
import com.cast.desafiobanco.api.exception.ResourceNotFoundException;
import com.cast.desafiobanco.api.repository.ContaRepository;
import com.cast.desafiobanco.api.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.cast.desafiobanco.api.shared.Constantes.*;

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
    public Conta findById(Long id) {
        return contaRepository.findById(id).
                orElseThrow( () -> new ResourceNotFoundException(CONTA_NAO_EXISTENTE));
    }

    @Override
    public Conta findByNumeroConta(Long numeroConta) {
        return contaRepository.findByNumeroConta(numeroConta)
                .orElseThrow( () -> new ResourceNotFoundException(CONTA_NAO_EXISTENTE));
    }

    @Override
    public void deleteById(Long id) {
        contaRepository.deleteById(id);
    }

    @Override
    public boolean vereficarLimiteAoCriarConta(Conta conta) {
        if(conta.getSaldo() >= VALOR_MINIMO_PARA_CRIAR_CONTA) {
            return true;
        }else{
            throw new DomainException(SALDO_INSUFICIENTE_PARA_ABERTURA_DE_CONTA);
        }
    }

    @Override
    public boolean vereficarLimiteMinimo(Double valor) {
        if(valor <= VALOR_MAXIMO_PARA_TRANSFERENCIA) {
            return true;
        }else {
            throw new DomainException(VALOR_MAXIMO_DE_OPERACAO);
        }
    }

    @Override
    public boolean vereficarSaldoNaConta(Conta conta, Double valor) {
        if (valor <= conta.getSaldo()) {
            return true;
        }else{
            throw new DomainException(SALDO_INSUFICIENTE);
        }
    }

    @Override
    public Conta depositar(Conta conta, Double valor) {
      conta.setSaldo( conta.getSaldo() + valor);
      return contaRepository.save(conta);
    }

    @Override
    public Conta sacar(Conta conta, Double valor) {
        conta.setSaldo( conta.getSaldo() - valor);
        return contaRepository.save(conta);
    }

    @Override
    public Long gerarNumeroDaConta() {
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
