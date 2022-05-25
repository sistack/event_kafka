package com.cfa.objects.controller;

import com.cfa.objects.letter.Letter;
import com.cfa.objects.repository.LetterRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.hazelcast.spi.impl.operationservice.impl.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public abstract class LetterController  {
    @Autowired
    LetterRepository letterRepository;
    private Letter letter;

    // Les API
    // findByID
    @GetMapping("/letter")
    public Letter getById(@RequestParam(value = "id") int id){
        return letterRepository.getOne(id);
    }
    // save
    @PostMapping
    public Letter save(Letter entity) {
        return letterRepository.save(entity);
    }

    // getAll
    @GetMapping("/letters")
    public List<Letter> getAll(){
        return letterRepository.findAll();
    }



    // fait appel au repo

}
