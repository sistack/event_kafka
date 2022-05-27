package com.cfa.jobs.jobLetter;

import com.cfa.jobs.jobexample.ProcessorExample;
import com.cfa.jobs.jobexample.ReaderExample;
import com.cfa.jobs.jobexample.WriterExample;
import com.cfa.objects.controller.LetterController;
import com.cfa.objects.letter.Letter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.integration.chunk.RemoteChunkingManagerStepBuilderFactory;
import org.springframework.batch.integration.config.annotation.EnableBatchIntegration;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Arrays;

@Configuration
@EnableBatchIntegration
@EnableBatchProcessing
@EnableKafka
public class RemoteJobMasterConfiguration {
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private RemoteChunkingManagerStepBuilderFactory managerStepBuilderFactory;

   @Autowired
   private LetterController letterController;
   @Bean
   public LetterController letterController(){
       return null;
   }
    /*
     * Configure outbound flow (requests going to workers)
     */
    @Bean
    public DirectChannel requests() {
        return new DirectChannel();
    }
    /*
     * Configure inbound flow (replies coming from workers)
     */
    @Bean
    public QueueChannel replies() {
        return new QueueChannel();
    }
    // Bean de Job
    @Bean
    public Job letterJob() {
        return jobBuilderFactory.get("letterJob")
                .start(step1())
                .next(managerStep())
                .build();
    }
    @Bean
    public Step step1() {
        return this.stepBuilderFactory
                .get("step1")
                .<Letter, Letter>chunk(4)
                .reader(new Reader())
                .processor(new Processor())
                .writer(new Writer())
                .build();
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
}
