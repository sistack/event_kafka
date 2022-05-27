package com.cfa.letterjobservice;

import com.cfa.customjobservice.JobProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.integration.chunk.RemoteChunkingManagerStepBuilderFactory;
import org.springframework.batch.integration.chunk.RemoteChunkingWorkerBuilder;
import org.springframework.batch.integration.config.annotation.EnableBatchIntegration;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.kafka.annotation.EnableKafka;

import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableBatchIntegration
@EnableBatchProcessing
@EnableKafka
public class WorkerConfiguration {
    @Autowired
    private RemoteChunkingWorkerBuilder workerBuilder;
    @Autowired
    private DirectChannel requests;
    @Autowired
    private QueueChannel replies;
    @Autowired
    private JobProcessor jobProcessor;
    @Autowired
    private Writer writer;

    // inbound : écoute
    @Bean
    public IntegrationFlow workerFlow() {
        return this.workerBuilder
                .inputChannel(requests) // requests received from the manager
                .outputChannel(replies) // replies sent to the manager
                .itemProcessor((ItemProcessor) jobProcessor)
                .itemWriter(writer)
                .build();
    }

    @Bean
    public IntegrationFlow outboundFlow() {
        return IntegrationFlows
                .from(replies)
                .get();
    }
/*
 @Bean
    public Step managerStep() {
        return this.managerStepBuilderFactory.get("masterStep")
                .chunk(10)
                .reader(new Reader())
                .outputChannel(requests) // requests sent to workers
                .inputChannel(replies) // replies received from workers
                .build();

    }
    @Autowired
    private RemoteChunkingManagerStepBuilderFactory managerStepBuilderFactory;
 */

}
