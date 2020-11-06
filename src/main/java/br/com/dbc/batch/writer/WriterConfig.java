package br.com.dbc.batch.writer;

import br.com.dbc.model.Receita;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class WriterConfig {

    @Bean
    @StepScope
    public FlatFileItemWriter<Receita> writer(@Value("#{jobParameters['input-file']}") Resource resource){
        return new FlatFileItemWriterBuilder<Receita>()
                .encoding("utf-8")
                .name("writer")
                .resource(resource)
                .delimited()
                .delimiter(";")
                .names("agencia","conta","saldo","status","atualizacao")
                .build();
    }
}
