package com.cast.desafiobanco.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data @AllArgsConstructor @NoArgsConstructor
public class ContaDto {
    private String nome;
    @NotEmpty(message = "{cpf.not.empty}")
    @Size(max = 11, message = "{cpf.invalid}")
    private String cpf;
    private Double saldo;
}
