package br.com.dbc.batch.step;

import br.com.dbc.model.Receita;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfig {
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step(ItemReader<Receita> reader,
                     ItemWriter<Receita> writer){
        return stepBuilderFactory
                .get("step")
                .<Receita,Receita> chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }
}
