package br.com.dbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertThrows;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class MainApplicationTest {

    @Test
    @DisplayName("Retorna Exception caso não passe nada como parametro")
    public void retonarException() {
        assertThrows(IllegalArgumentException.class, ()-> new SincronizacaoReceitaApplication().main(new String[]{}));
    }

}
