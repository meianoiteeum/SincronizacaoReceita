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
import java.io.Writer;

@Configuration
public class WriterConfig {

    private String extensao = ".csv";
    private String horaAtual = String.valueOf(System.currentTimeMillis());
    private String AGENCIA = "agencia";
    private String CONTA = "conta";
    private String SALDO = "saldo";
    private String STATUS = "status";
    private String ATUALIZACAO = "atualizacao";

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
                .lineAggregator(lineAggregator())
                .headerCallback(header())
                .delimited()
                .delimiter(";")
                .names(AGENCIA,CONTA,SALDO,STATUS,ATUALIZACAO)
                .build();
    }

    private FlatFileHeaderCallback header() {
        return new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.append(AGENCIA).append(";");
                writer.append(CONTA).append(";");
                writer.append(SALDO).append(";");
                writer.append(STATUS).append(";");
                writer.append(ATUALIZACAO);
            }
        };
    }

    private LineAggregator<Receita> lineAggregator() {
        return new LineAggregator<Receita>() {
            @Override
            public String aggregate(Receita receita) {
                StringBuilder builder = new StringBuilder();
                builder.append(receita.getAgencia()).append(";");
                builder.append(convertConta(receita.getConta())).append(";");
                builder.append(convertSaldo(receita.getSaldo())).append(";");
                builder.append(receita.getStatus()).append(";");
                builder.append(receita.getAtualizacao());
                return builder.toString();
            }

            private String convertSaldo(Double saldo) {
                return saldo.toString().replace(".",",");
            }

            private String convertConta(String conta) {
                int lenght = conta.length();
                String digito = conta.substring(lenght - 1);
                String numero = conta.substring(0,lenght - 1).concat("-").concat(digito);
                return numero;
            }
        };
    }
}
