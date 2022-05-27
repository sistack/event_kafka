package com.cfa.objects.repository;

import com.cfa.objects.letter.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
@Service
public interface LetterRepository extends JpaRepository<Letter, Integer> {

}