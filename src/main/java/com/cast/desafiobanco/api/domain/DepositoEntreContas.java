package com.cast.desafiobanco.api.domain;

import lombok.Data;

@Data
public class DepositoEntreContas {
    Long contaSolicitante;
    Double valor;
    Long contaDoBeneficiario;
}
