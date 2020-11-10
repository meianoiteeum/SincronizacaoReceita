package br.com.dbc.batch.writer;

import br.com.dbc.model.Receita;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Configuration
public class WriterConfig {

    private String extensao = ".csv";
    private String horaAtual = String.valueOf(System.currentTimeMillis());

    @Bean
    @StepScope
    public FlatFileItemWriter<Receita> writer(@Value("#{jobParameters['input-file']}") Resource resource) throws IOException {
        File file = resource.getFile();
        String path = file.getAbsolutePath();
        File novo = new File(path.replace(extensao, horaAtual).concat(".csv"));

        return new FlatFileItemWriterBuilder<Receita>()
                .encoding("utf-8")
                .name("writer")
                .resource(new FileSystemResource(novo))
                .delimited()
                .delimiter(";")
                .names("agencia","conta","saldo","status","atualizacao")
                .build();
    }
}
