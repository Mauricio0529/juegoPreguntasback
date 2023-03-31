package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface questionRepository extends JpaRepository<question, Integer> {

    public List<question> findByCategoryId(Integer id);

/*
    @Query(value = "SELECT " + "* " + "FROM question")
    public question getQuestion();*/
}