package br.com.dbc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Receita {
    private Integer agencia;
    private String conta;
    private Double saldo;
    private Character status;

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
