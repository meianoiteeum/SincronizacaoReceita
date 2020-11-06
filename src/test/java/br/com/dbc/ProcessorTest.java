package br.com.dbc;

import br.com.dbc.batch.processor.ProcessorConfig;
import br.com.dbc.model.Receita;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBatchTest
public class ProcessorTest {

    @InjectMocks
    ProcessorConfig processorConfig;

    Receita receita;

    @Before
    public void before(){
        receita = Receita.builder()
                .conta("0101")
                .agencia("12225-6")
                .saldo(100.1)
                .status("A")
                .atualizacao(true)
                .build();
    }
}
