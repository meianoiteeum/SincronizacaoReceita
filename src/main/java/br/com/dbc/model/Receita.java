package br.com.dbc.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Receita {
    private String agencia;
    private String conta;
    private Double saldo;
    private String status;
    private Boolean atualizacao;
}
