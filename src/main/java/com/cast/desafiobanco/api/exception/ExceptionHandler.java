package com.cast.desafiobanco.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> HandlerDomain(DomainException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Exception exception = new Exception();
        exception.setStatus(status.value());
        exception.setTitulo(ex.getMessage());
        exception.setDataHora(OffsetDateTime.now());
        return handleExceptionInternal(ex,exception,new HttpHeaders(),status,request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Exception exception = new Exception();
        exception.setStatus(status.value());
        exception.setDataHora(OffsetDateTime.now());
        exception.setTitulo(ex.getMessage());
        return handleExceptionInternal(ex,exception,new HttpHeaders(),status,request);
    }
}
