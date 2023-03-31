package com.example.demo.repository;

import com.example.demo.models.answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface answerRepository extends JpaRepository<answer, Integer> {
    public answer findByQuestion(Integer idQuestion);
}