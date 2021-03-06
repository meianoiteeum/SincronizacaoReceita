package br.com.dbc.batch.step;

import br.com.dbc.batch.reader.CustomReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfig {
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Step step(FlatFileItemReader reader,
                     ItemProcessor processor,
                     FlatFileItemWriter writer){
        return stepBuilderFactory
                .get("step")
                .chunk(5)
                .reader(new CustomReader(reader))
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(Integer.MAX_VALUE)
                .build();
    }
}
