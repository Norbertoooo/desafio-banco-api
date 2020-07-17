package com.cast.desafiobanco.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data @NoArgsConstructor @AllArgsConstructor
public class TransferenciaDto {
    @NotNull
    Long contaDoSolicitante;
    @NotNull
    Double valor;
    @NotNull
    Long contaDoBeneficiario;
}
