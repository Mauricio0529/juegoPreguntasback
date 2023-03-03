package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.question;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface questionRepository extends JpaRepository<question, Integer> {

    public List<question> findByCategoryId(Integer id);

//    public List<question> findByQuestion(Integer question);
/*
    @Query(value = "SELECT " + "* " + "FROM question")
    public question getQuestion();*/
    // public question addquestionToAnswer(Integer asnwerId, Integer questionId);
}