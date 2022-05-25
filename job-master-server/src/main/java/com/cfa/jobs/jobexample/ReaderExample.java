package com.cfa.jobs.jobexample;

import com.cfa.objects.letter.Letter;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ReaderExample implements ItemReader<Letter> {

    @Override
    public Letter read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
