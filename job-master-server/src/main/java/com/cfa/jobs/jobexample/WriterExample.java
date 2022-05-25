package com.cfa.jobs.jobexample;

import com.cfa.objects.letter.Letter;
import org.springframework.batch.item.ItemWriter;

import java.util.List;


public class WriterExample implements ItemWriter<Letter> {

    @Override
    public void write(List<? extends Letter> list) throws Exception {

    }
}
