package com.cfa.jobs.jobLetter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@EnableBinding({Source.class})
public class LetterJobTaskletSource implements Tasklet, StepExecutionListener {

    private final Source source;

    public void beforeStep(final StepExecution parStepExecution){
        log.debug("BeforeStep Asset of MyFirstJob tasket initialized");
    }

    public RepeatStatus execute(final StepContribution parStepContribution, final ChunkContext parChunkContext) {

        final Map<String, Object> locParameters =
                parStepContribution.getStepExecution().getJobParameters().getParameters()
                        .entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getValue()));

        // creating the message to send
        final String locPayload = (String) locParameters.get("value");
        final Message<String> locPartitionKey = MessageBuilder.withPayload(locPayload)
                .setHeader("custom_info", "start")
                .build();
        log.info("Message to send for MyFirstJob Tasklet : " + locPayload);
        source.output().send(locPartitionKey);

        return RepeatStatus.FINISHED;
    }

    
    public ExitStatus afterStep(final StepExecution parStepExecution) {
        log.debug("AfterStep executing tasklet");
        return ExitStatus.UNKNOWN;
    }


}
