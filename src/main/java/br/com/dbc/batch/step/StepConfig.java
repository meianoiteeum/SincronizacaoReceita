package br.com.dbc.batch.step;

import br.com.dbc.batch.reader.CustomReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfig {
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step(FlatFileItemReader reader,
                     ItemWriter writer){
        return stepBuilderFactory
                .get("step")
                .chunk(1)
                .reader(new CustomReader(reader))
                .writer(writer)
                .build();
    }
}
