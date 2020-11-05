package br.com.dbc.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Receita {
    private String agencia;
    private String conta;
    private Double saldo;
    private String status;

    @Override
    public String toString() {
        return "Receita{" +
                "agencia=" + agencia +
                ", conta='" + conta + '\'' +
                ", saldo=" + saldo +
                ", status=" + status +
                '}';
    }
}
