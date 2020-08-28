package com.cast.desafiobanco.api.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensagem) { super(mensagem);}
}
