package br.com.dbc.job;

import br.com.dbc.listener.ListenerConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfig {
    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job job(ListenerConfig listenerConfig, Step step){
        return jobBuilderFactory
                .get("job")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .listener(listenerConfig)
                .build();
    }
}
