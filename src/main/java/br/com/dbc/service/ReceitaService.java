package br.com.dbc.service;

public interface ReceitaService {
    boolean atualizarConta(String agencia, String conta, double saldo, String status)
            throws RuntimeException, InterruptedException;
}
