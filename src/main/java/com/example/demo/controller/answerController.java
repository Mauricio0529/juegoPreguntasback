package com.example.demo.controller;

import com.example.demo.dto.responseDto.answerResponseDto;
import com.example.demo.service.answerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class answerController {

    @Autowired
    private answerService answerService;

    @GetMapping("/get/{id}")
    public ResponseEntity<answerResponseDto> getAnswerById(@PathVariable final Integer id) {
        return ResponseEntity.ok(answerService.getAnswer(id));
    }

    @GetMapping("/validateAnswer/{id}")
    public ResponseEntity<Boolean> requestAnswerCorrect(@PathVariable final Integer id) {
        return ResponseEntity.ok(answerService.validateAnswerCorrect(id));
    }

}