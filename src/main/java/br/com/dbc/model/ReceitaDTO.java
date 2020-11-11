package br.com.dbc.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaDTO {
    private String agencia;
    private String conta;
    private String saldo;
    private String status;
}
