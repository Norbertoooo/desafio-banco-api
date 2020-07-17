package com.cast.desafiobanco.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exception {
    private String titulo;
    private OffsetDateTime dataHora;
    private Integer status;
}
