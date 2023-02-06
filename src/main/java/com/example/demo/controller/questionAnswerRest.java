package com.example.demo.controller;

import com.example.demo.dto.responseDto.questionResponseDto;
import com.example.demo.service.questionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class questionAnswerRest {

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

/*
    @GetMapping("/getAllQuestionModelMapper")
    private ResponseEntity<List<questionDTO>> getAllQuestion(){
        return ResponseEntity.ok(questionService.getAllQuestionModelMapper());
    }

    @GetMapping("/pregunta")
    private ResponseEntity<List<questionDTO>> obtenerPregunta() {
        return ResponseEntity.ok(questionService.pregunta());
    }

    // https://github.com/vdoble-dev/cc/tree/master/src/main/java/edu/uancv
    @GetMapping("/answer")
    private ResponseEntity<List<answer>> obtenerAnswer() {
        return ResponseEntity.ok(questionService.answers());
    }
*/
}