package br.com.dbc.service;

import br.com.dbc.service.impl.ReceitaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ReceitaServiceTest {
    ReceitaService service;

    @BeforeEach
    public void setUp(){
        this.service = new ReceitaServiceImpl();
    }

    @Test
    @DisplayName("Passar todos os parâmetros corretos para retornar true")
    public void objetoCompleto() throws InterruptedException {
        String AGENCIA = "0101";
        String CONTA = "122268";
        Double SALDO = 0.0;
        String STATUS = "A";

        Boolean atualizacao = service.atualizarConta(AGENCIA,CONTA,SALDO,STATUS);

        assertThat(atualizacao).isEqualTo(true);
    }

    @Test
    @DisplayName("Passar todos os parâmetros corretos menos a agencia")
    public void objetoCompletoComErroNaAgencia() throws InterruptedException {
        String AGENCIA = "010";
        String CONTA = "122268";
        Double SALDO = 0.0;
        String STATUS = "A";

        Boolean atualizacao = service.atualizarConta(AGENCIA,CONTA,SALDO,STATUS);

        assertThat(atualizacao).isEqualTo(false);
    }

    @Test
    @DisplayName("Passar todos os parâmetros corretos menos a agencia que vai ser nulo")
    public void objetoCompletoComErroNaAgenciaComNulo() throws InterruptedException {
        String AGENCIA = "010";
        String CONTA = null;
        Double SALDO = 0.0;
        String STATUS = "A";

        Boolean atualizacao = service.atualizarConta(AGENCIA,CONTA,SALDO,STATUS);

        assertThat(atualizacao).isEqualTo(false);
    }

    @Test
    @DisplayName("Passar todos os parâmetros corretos menos a conta")
    public void objetoCompletoComErroNaConta() throws InterruptedException {
        String AGENCIA = "0101";
        String CONTA = "12226-8";
        Double SALDO = 0.0;
        String STATUS = "A";

        Boolean atualizacao = service.atualizarConta(AGENCIA,CONTA,SALDO,STATUS);

        assertThat(atualizacao).isEqualTo(false);
    }

    @Test
    @DisplayName("Passar todos os parâmetros corretos menos a conta que vai ser nulo")
    public void objetoCompletoComErroNaContaComNulo() throws InterruptedException {
        String AGENCIA = "0101";
        String CONTA = null;
        Double SALDO = 0.0;
        String STATUS = "A";

        Boolean atualizacao = service.atualizarConta(AGENCIA,CONTA,SALDO,STATUS);

        assertThat(atualizacao).isEqualTo(false);
    }

    @Test
    @DisplayName("Passar todos os parâmetros corretos menos o status")
    public void objetoCompletoComErroNoStatus() throws InterruptedException {
        String AGENCIA = "0101";
        String CONTA = "122268";
        Double SALDO = 0.0;
        String STATUS = "O";

        Boolean atualizacao = service.atualizarConta(AGENCIA,CONTA,SALDO,STATUS);

        assertThat(atualizacao).isEqualTo(false);

    }

    @Test
    @DisplayName("Passar todos os parâmetros corretos menos o status que vai ser nulo")
    public void objetoCompletoComErroNoStatusComNulo() throws InterruptedException {
        String AGENCIA = "0101";
        String CONTA = "122268";
        Double SALDO = 0.0;
        String STATUS = null;

        Boolean atualizacao = service.atualizarConta(AGENCIA,CONTA,SALDO,STATUS);

        assertThat(atualizacao).isEqualTo(false);
    }

}
