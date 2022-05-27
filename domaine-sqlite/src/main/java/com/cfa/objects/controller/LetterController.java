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
    @GetMapping("/letters")
    public List<Letter> getAll(){
        return letterRepository.findAll();
    }
    // findByID
   // ("/getById/id")
    //@RequestParam(value = "id") int id, @PathVariable String id
   // public ResponseData getUser(@PathVariable Long id)
    @GetMapping("/getById/{id}")
    public Letter getById(@PathVariable("id") int id){
        return letterRepository.getOne(id);
    }
    // save
    @PostMapping("/postLetter/l")
    public Letter save(@RequestBody Letter l) {
        return letterRepository.save(l);
    }

}
