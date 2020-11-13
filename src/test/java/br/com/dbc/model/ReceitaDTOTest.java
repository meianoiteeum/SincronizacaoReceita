package br.com.dbc.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class ReceitaDTOTest {

    @Test
    @DisplayName("Verificar o contrutor sem argumentos e passando nenhum valor pelo set")
    public void testarContrutoresSemArgumentos(){
        ReceitaDTO receita = new ReceitaDTO();

        assertThat(receita.getAgencia()).isEqualTo(null);
        assertThat(receita.getConta()).isEqualTo(null);
        assertThat(receita.getSaldo()).isEqualTo(null);
        assertThat(receita.getStatus()).isEqualTo(null);
    }

    @Test
    @DisplayName("Verificar o contrutor com argumentos")
    public void testarContrutoresComArgumentos(){
        ReceitaDTO receita = new ReceitaDTO("0101","12225-6","0.0","A");

        assertThat(receita.getAgencia()).isEqualTo("0101");
        assertThat(receita.getConta()).isEqualTo("12225-6");
        assertThat(receita.getSaldo()).isEqualTo("0.0");
        assertThat(receita.getStatus()).isEqualTo("A");
    }

    @Test
    @DisplayName("Verificar os setters")
    public void testarSetters(){
        ReceitaDTO receita = new ReceitaDTO();
        receita.setAgencia("0101");
        receita.setConta("12225-6");
        receita.setSaldo("0.0");
        receita.setStatus("A");

        assertThat(receita.getAgencia()).isEqualTo("0101");
        assertThat(receita.getConta()).isEqualTo("12225-6");
        assertThat(receita.getSaldo()).isEqualTo("0.0");
        assertThat(receita.getStatus()).isEqualTo("A");
    }

    @Test
    @DisplayName("Verificar o Builder")
    public void testarBuilder(){
        ReceitaDTO receita = ReceitaDTO.builder()
                .agencia("0101")
                .conta("12225-6")
                .saldo("0.0")
                .status("A")
                .build();

        assertThat(receita).isNotNull();
        assertThat(receita.getAgencia()).isEqualTo("0101");
        assertThat(receita.getConta()).isEqualTo("12225-6");
        assertThat(receita.getSaldo()).isEqualTo("0.0");
        assertThat(receita.getStatus()).isEqualTo("A");
    }
}
