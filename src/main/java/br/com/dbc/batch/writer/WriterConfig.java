package br.com.dbc.batch.writer;

import br.com.dbc.model.Receita;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterConfig {
    @Bean
    public ItemWriter<Receita> writer(){
        return item -> item.forEach(System.out::println);
    }
}
