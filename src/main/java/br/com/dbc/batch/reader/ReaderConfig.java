package br.com.dbc.batch.reader;

import br.com.dbc.model.Receita;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Receita> reader(@Value("#{jobParameters['arquivoReceita']}") Resource resource){
        return new FlatFileItemReaderBuilder<Receita>()
                .name("reader")
                .encoding("utf-8")
                .resource(resource)
                .delimited()
                .delimiter(";")
                .names(new String[]{"agencia","conta","saldo","status"})
                .targetType(Receita.class)
                .build();
    }
}
