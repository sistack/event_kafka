package com.cfa.jobs.jobLetter;

import com.cfa.objects.letter.Letter;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

public class Reader implements ItemReader<Letter> {

    @Override
    public Letter read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}