package com.cfa.jobs.jobLetter;

import com.cfa.jobs.jobexample.ReaderExample;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.integration.chunk.RemoteChunkingManagerStepBuilderFactory;
import org.springframework.batch.integration.config.annotation.EnableBatchIntegration;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import java.io.File;
import java.util.Arrays;
/*
* Manager
 */
// classe de config des channels
@Configuration
@EnableBatchIntegration
@EnableBatchProcessing
@EnableKafka
public class RemoteChannelChunkingConfig {
    @Autowired
    private RemoteChunkingManagerStepBuilderFactory managerStepBuilderFactory;
    @Bean
    public DirectChannel requests() {
        return new DirectChannel();
    }
    @Bean
    public QueueChannel replies() {
        return new QueueChannel();
    }

    @Bean
    public Step managerStep() {
        return this.managerStepBuilderFactory.get("managerStep")
                .chunk(10)
                .reader(new Reader())
                .outputChannel(requests()) // requests sent to workers
                .inputChannel(replies())// replies received from workers
                .build();
    }



    // exemple sur le worker
    // coté master worker déclarer inbound outbound
    // faire en sorte inbound config cote worker est l'inverse de coté master entre in et out
    // les topics spécifiés entre les 2
    // configuration cannaux cote master ou worker
    // masyer envoi msg sur topic 1 que worker ecoute msg sur topic 1 et wotker envoie msg sur topib b et worker
    // séparer topic entre envoie et réception sur master et worker


}
