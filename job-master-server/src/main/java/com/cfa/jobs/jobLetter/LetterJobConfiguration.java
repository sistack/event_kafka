package com.cfa.jobs.jobLetter;

import com.cfa.jobs.jobexample.ProcessorExample;
import com.cfa.jobs.jobexample.ReaderExample;
import com.cfa.jobs.jobexample.WriterExample;
import com.cfa.objects.letter.Letter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;

public class LetterJobConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    private Source sources;


    @Bean
    public Job letterJob() {
        return jobBuilderFactory.get("letterJob")
                .start(step1())
                .build();
    }
    @Bean
    public Step step1() {
        return this.stepBuilderFactory
                .get("step1")
                .<Letter, Letter>chunk(1)
                .reader(new ReaderExample())
                .processor(new ProcessorExample())
                .writer(new WriterExample())
                .build();
    }
}
