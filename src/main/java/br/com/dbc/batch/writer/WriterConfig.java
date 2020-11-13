package br.com.dbc.batch.writer;

import br.com.dbc.model.Receita;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

@Configuration
public class WriterConfig {

    private static final String EXTENSION = ".csv";
    private static final String SEMICOLON = ";";
    private static final String DOT = ".";
    private static final String COMMA = ",";
    private static final String HYPHEN = "-";
    private static final String AGENCIA = "agencia";
    private static final String CONTA = "conta";
    private static final String SALDO = "saldo";
    private static final String STATUS = "status";
    private static final String ATUALIZACAO = "atualizacao";

    @Bean
    @StepScope
    public FlatFileItemWriter<Receita> writer(@Value("#{jobParameters['input-file']}") Resource resource) throws IOException {
        File file = resource.getFile();
        String path = file.getAbsolutePath();
        String horaAtual = String.valueOf(System.currentTimeMillis());
        File novo = new File(path.replace(EXTENSION, horaAtual).concat(EXTENSION));

        return new FlatFileItemWriterBuilder<Receita>()
                .encoding("utf-8")
                .name("writer")
                .resource(new FileSystemResource(novo))
                .lineAggregator(lineAggregator())
                .headerCallback(header())
                .delimited()
                .delimiter(SEMICOLON)
                .names(AGENCIA,CONTA,SALDO,STATUS,ATUALIZACAO)
                .build();
    }

    private FlatFileHeaderCallback header() {
        return writer -> {
            writer.append(AGENCIA).append(SEMICOLON);
            writer.append(CONTA).append(SEMICOLON);
            writer.append(SALDO).append(SEMICOLON);
            writer.append(STATUS).append(SEMICOLON);
            writer.append(ATUALIZACAO);
        };
    }

    private LineAggregator<Receita> lineAggregator() {
        return new LineAggregator<>() {
            @Override
            public String aggregate(Receita receita) {
                StringBuilder builder = new StringBuilder();
                builder.append(receita.getAgencia()).append(SEMICOLON);
                builder.append(convertConta(receita.getConta())).append(SEMICOLON);
                builder.append(convertSaldo(receita.getSaldo())).append(SEMICOLON);
                builder.append(receita.getStatus()).append(SEMICOLON);
                builder.append(receita.getAtualizacao());
                return builder.toString();
            }

            private String convertSaldo(Double saldo) {
                return saldo.toString().replace(DOT, COMMA);
            }

            private String convertConta(String conta) {
                int lenght = conta.length();
                String digito = conta.substring(lenght - 1);
                String numero = conta.substring(0, lenght - 1).concat(HYPHEN).concat(digito);
                return numero;
            }
        };
    }
}
