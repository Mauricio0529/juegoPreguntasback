package com.example.demo.controller;

import com.example.demo.dto.responseDto.questionResponseDto;
import com.example.demo.models.question;
import com.example.demo.service.questionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class questionController {

    @Autowired
    private questionServiceImpl questionService;

    @GetMapping("/getNumberRandom") // este es el numero random de la formula
    private ResponseEntity<Integer> getNumberRandom(){
        return ResponseEntity.ok(questionService.generarNumeroAleatorio());
    }

    @GetMapping("/allQuestion")
    private  ResponseEntity<List<questionResponseDto>> getQuestionList() {
        return ResponseEntity.ok(questionService.getAllQuestion());
    }

    @GetMapping("/getQuestionRandom") // este se obtiene la pregunta y el id(numero de la pregunta)
    private ResponseEntity<List<questionResponseDto>> getQuestion() {
        return ResponseEntity.ok(questionService.getQuestion());
    }

    @GetMapping("/getQuestionByCategory/{id}")
    private ResponseEntity<List<questionResponseDto>> getQuestionByCategory(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(questionService.getQuestionByCategory(id));
    }
}