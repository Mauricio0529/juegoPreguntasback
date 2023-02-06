package com.example.demo.repository;

import com.example.demo.dto.answerDTO;
import com.example.demo.models.answer;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.question;
import org.springframework.stereotype.Repository;

@Repository
public interface questionRepository extends JpaRepository<question, Integer> {
//    public List<question> findByQuestion(Integer question);
/*
    @Query(value = "SELECT " + "* " + "FROM question")
    public question getQuestion();*/
    // public question addquestionToAnswer(Integer asnwerId, Integer questionId);
}