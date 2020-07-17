package com.cast.desafiobanco.api.exception;

public class DomainException extends RuntimeException{

    public DomainException(String mensagem) {
        super(mensagem);
    }
}
