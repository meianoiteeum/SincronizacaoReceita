package br.com.dbc.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReceitaDTO {
    private String agencia;
    private String conta;
    private String saldo;
    private String status;
}
