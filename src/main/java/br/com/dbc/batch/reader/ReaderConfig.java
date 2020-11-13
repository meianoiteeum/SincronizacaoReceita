package br.com.dbc.batch.reader;

import br.com.dbc.model.ReceitaDTO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ReaderConfig {

    private static final String AGENCIA = "agencia";
    private static final String CONTA = "conta";
    private static final String SALDO = "saldo";
    private static final String STATUS = "status";

    @Bean
    @StepScope
    @SuppressWarnings({"rawtypes", "unchecked"})
    public FlatFileItemReader reader(@Value("#{jobParameters['input-file']}") Resource resource){
        return new FlatFileItemReaderBuilder()
                .name("reader")
                .encoding("utf-8")
                .resource(resource)
                .linesToSkip(1)
                .delimited()
                .delimiter(";")
                .names(AGENCIA,CONTA,SALDO,STATUS)
                .targetType(ReceitaDTO.class)
                .build();
    }
}
