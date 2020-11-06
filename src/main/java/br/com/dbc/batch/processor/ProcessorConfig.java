package br.com.dbc.batch.processor;

import br.com.dbc.model.Receita;
import br.com.dbc.service.ReceitaService;
import br.com.dbc.service.impl.ReceitaServiceImpl;
import lombok.SneakyThrows;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorConfig {

    private ReceitaService receitaService = new ReceitaServiceImpl();

    @Bean
    public ItemProcessor<Receita, Receita> processor() {
        ValidatingItemProcessor<Receita> validatingProcessor = new ValidatingItemProcessor<>();
        validatingProcessor.setValidator(validator());
        validatingProcessor.setFilter(true);
        return validatingProcessor;
    }

    private Validator<? super Receita> validator() {
        return new Validator<Receita>() {
            @Override
            public void validate(Receita receita) throws ValidationException {
                try {
                    Boolean atualizacao = receitaService.atualizarConta(receita.getAgencia(), receita.getConta(), receita.getSaldo(), receita.getStatus());
                    receita.setAtualizacao(atualizacao);
                }catch (RuntimeException | InterruptedException runtimeException){
                    throw new ValidationException(runtimeException.getMessage());
                }
            }
        };
    }
}
