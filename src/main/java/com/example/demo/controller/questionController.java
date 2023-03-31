package com.example.demo.controller;

import com.example.demo.dto.responseDto.questionResponseDto;
import com.example.demo.service.questionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/points")
    private ResponseEntity<Integer> validatePointsQuestion() {
        return  ResponseEntity.ok(questionService.valuePointQuestion());
    }

    @GetMapping("/getQuestion/{id}")
    private ResponseEntity<List<questionResponseDto>> getQuestionByCategory(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(questionService.getQuestion(id));
    }

    @GetMapping("/validateQuestion/{answerSelected}")
    private ResponseEntity<Boolean> validateCorrectQuestion(@PathVariable("answerSelected") boolean selectedQuestion) {
        return ResponseEntity.ok(questionService.validateCorrectQuestion(selectedQuestion));
    }
}