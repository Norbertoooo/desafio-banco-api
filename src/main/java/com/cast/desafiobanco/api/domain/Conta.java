package com.cast.desafiobanco.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double saldo;
    private String cpf;
    private Long numeroConta;

    public Conta(String nome, Double saldo, String cpf, Long numero_conta) {
        this.nome = nome;
        this.saldo = saldo;
        this.cpf = cpf;
        this.numeroConta = numero_conta;
    }
}
