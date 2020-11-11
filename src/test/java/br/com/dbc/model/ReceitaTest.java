package br.com.dbc.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ReceitaTest {

    @Test
    @DisplayName("Verificar o contrutor sem argumentos e passando nenhum valor pelo set")
    public void testarContrutoresSemArgumentos(){
        Receita receita = new Receita();

        assertThat(receita.getAgencia()).isEqualTo(null);
        assertThat(receita.getConta()).isEqualTo(null);
        assertThat(receita.getSaldo()).isEqualTo(null);
        assertThat(receita.getStatus()).isEqualTo(null);
        assertThat(receita.getAtualizacao()).isEqualTo(null);
    }

    @Test
    @DisplayName("Verificar o contrutor com argumentos")
    public void testarContrutoresComArgumentos(){
        Receita receita = new Receita("0101","12225-6",0.0,"A",false);

        assertThat(receita.getAgencia()).isEqualTo("0101");
        assertThat(receita.getConta()).isEqualTo("12225-6");
        assertThat(receita.getSaldo()).isEqualTo(0.0);
        assertThat(receita.getStatus()).isEqualTo("A");
        assertThat(receita.getAtualizacao()).isEqualTo(false);
    }

    @Test
    @DisplayName("Verificar os setters")
    public void testarSetters(){
        Receita receita = new Receita();
        receita.setAgencia("0101");
        receita.setConta("12225-6");
        receita.setSaldo(0.0);
        receita.setStatus("A");
        receita.setAtualizacao(false);

        assertThat(receita.getAgencia()).isEqualTo("0101");
        assertThat(receita.getConta()).isEqualTo("12225-6");
        assertThat(receita.getSaldo()).isEqualTo(0.0);
        assertThat(receita.getStatus()).isEqualTo("A");
        assertThat(receita.getAtualizacao()).isEqualTo(false);
    }

    @Test
    @DisplayName("Verificar o Builder")
    public void testarBuilder(){
        Receita receita = Receita.builder()
                .agencia("0101")
                .conta("12225-6")
                .saldo(0.0)
                .status("A")
                .atualizacao(false)
                .build();

        assertThat(receita).isNotNull();
        assertThat(receita.getAgencia()).isEqualTo("0101");
        assertThat(receita.getConta()).isEqualTo("12225-6");
        assertThat(receita.getSaldo()).isEqualTo(0.0);
        assertThat(receita.getStatus()).isEqualTo("A");
        assertThat(receita.getAtualizacao()).isEqualTo(false);
    }
}
