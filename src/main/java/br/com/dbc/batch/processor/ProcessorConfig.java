package br.com.dbc.batch.processor;

import br.com.dbc.model.Receita;
import br.com.dbc.service.ReceitaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorConfig {

    @Autowired
    private ReceitaService receitaService;

    private Logger logger = LoggerFactory.getLogger(ProcessorConfig.class);

    public ProcessorConfig(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @Bean
    public ItemProcessor<Receita, Receita> processor() {
        ValidatingItemProcessor<Receita> validatingProcessor = new ValidatingItemProcessor<>();
        validatingProcessor.setValidator(validator());
        validatingProcessor.setFilter(true);
        return validatingProcessor;
    }

    private Validator<? super Receita> validator() {
        return (Validator<Receita>) receita -> {
            try {
                logger.info("Enviando a conta " + receita.getConta() + " para a receita");
                Boolean atualizacao = receitaService.atualizarConta(receita.getAgencia(), receita.getConta(), receita.getSaldo(), receita.getStatus());
                receita.setAtualizacao(atualizacao);
            }catch (RuntimeException | InterruptedException runtimeException){
                logger.error("Error Exception: " + runtimeException.getMessage());
                throw new ValidationException(runtimeException.getMessage());
            }
        };
    }


}
