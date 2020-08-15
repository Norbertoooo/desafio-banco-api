package com.cast.desafiobanco.api.dto;

import com.cast.desafiobanco.api.domain.Conta;
import com.cast.desafiobanco.api.exception.Exception;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @AllArgsConstructor @NoArgsConstructor
public class ResponseDto {

    private String mensagem;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Conta> corpo = new ArrayList<>();

    private Exception erro;

    public ResponseDto(String mensagem) {
        this.mensagem = mensagem;
    }

    public ResponseDto(String mensagem, Exception erro) {
        this.mensagem = mensagem;
        this.erro = erro;
    }

    public ResponseDto(String mensagem, Conta corpo) {
        this.mensagem = mensagem;
        this.corpo.add(corpo);
    }

}
