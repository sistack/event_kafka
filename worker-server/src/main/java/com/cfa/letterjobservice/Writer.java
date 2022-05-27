package com.cfa.letterjobservice;

import com.cfa.objects.letter.Letter;
import org.springframework.batch.item.ItemWriter;

import java.util.List;


public class Writer implements ItemWriter<Letter> {

    @Override
    public void write(List<? extends Letter> list) throws Exception {

    }
}
