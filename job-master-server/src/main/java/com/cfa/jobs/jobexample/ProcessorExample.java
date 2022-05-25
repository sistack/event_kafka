package com.cfa.jobs.jobexample;

import com.cfa.objects.letter.Letter;
import org.springframework.batch.item.ItemProcessor;


public class ProcessorExample implements ItemProcessor<Letter, Letter> {


    @Override
    public Letter process(Letter letter) throws Exception {
        return null;
    }
}
