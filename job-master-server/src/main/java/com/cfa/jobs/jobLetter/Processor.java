package com.cfa.jobs.jobLetter;

import com.cfa.objects.letter.Letter;
import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<Letter, Letter> {
    @Override
    public Letter process(Letter letter) throws Exception {
        return null;
    }
}
