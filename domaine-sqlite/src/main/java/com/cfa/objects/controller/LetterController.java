package com.cfa.objects.controller;

import com.cfa.objects.letter.Letter;
import com.cfa.objects.repository.LetterRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.hazelcast.spi.impl.operationservice.impl.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/event-kafka",produces= "application/json; charset=UTF-8")
public class LetterController {
    @Autowired
    private LetterRepository letterRepository;
    private Letter letter;

    // Les API
    // getAll
    @GetMapping("/")
    public List<Letter> getAll(){
        return letterRepository.findAll();
    }
    // findByID
    @GetMapping("/getById")
    public Letter getById(@RequestParam(value = "id") int id){
        return letterRepository.getOne(id);
    }
    // save
    @PostMapping("/postLetter/l")
    public Letter save(@RequestBody Letter l) {
        return letterRepository.save(l);
    }

}
