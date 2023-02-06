package com.example.demo.service;

import com.example.demo.dto.questionDTO;
import com.example.demo.dto.responseDto.questionResponseDto;
import com.example.demo.models.question;

import java.util.List;

public interface questionService {

    public question getQuestionId(Integer questionId);

    public questionResponseDto getQuestionById();

    public questionResponseDto addQuestion(questionDTO questionDTO);
    // public questionDTO updateQuestion(Integer id, questionDTO questionDTO);
    // public questionDTO deleteQuestion(Integer id, questionDTO questionDTO);

    public List<questionResponseDto> getAllQuestion();

    public List<questionDTO> getAllQuestionModelMapper();
}