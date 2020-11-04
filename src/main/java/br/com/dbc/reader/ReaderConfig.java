package br.com.dbc.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ReaderConfig {
    @Value("${HOMEPATHIN}")
    private String path;

    @StepScope
    @Bean
    public FlatFileItemReader reader(LineMapper lineMapper){
        return new FlatFileItemReaderBuilder<Object>()
                .name("reader")
                .encoding("utf-8")
                .resource(new ClassPathResource(path))
                .lineMapper(lineMapper)
                .build();
    }
}
