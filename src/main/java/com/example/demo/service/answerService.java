package com.example.demo.service;

import com.example.demo.dto.answerDTO;
import com.example.demo.dto.responseDto.answerResponseDto;
import com.example.demo.models.answer;

import java.util.List;

public interface answerService {

    public answerResponseDto addAnswer(answerDTO answerDTO);

    public answer getAnswerById(Integer id);
    public answerResponseDto getAnswer(Integer idAnswer);


    public Boolean validateAnswerCorrect(Integer idAnswerSelect);

    public answerResponseDto getAnswerByQuestionId(Integer idQuestion); //
    public answer getIdQuestionToAnswer(Integer idQuestion); //

    public answerResponseDto updateAnswer(Integer id, answerDTO answerDTO);
    public answerResponseDto deleteAnswer(Integer id, answerDTO answerDTO);
    public List<answerResponseDto> getAllAnswer();

    // agregar una pregunta a las respuesta
    public answerResponseDto addQuestionToAnswer(Integer idQuestionToAnswer, Integer idQueston);

    // eliminar preguntas de las respuestas
    public answerResponseDto deleteQuestionToAnswer(Integer idQuestionToAnswer, Integer idQueston);
    public answerResponseDto updateQuestionToAnswer(Integer idQuestionToAnswer, Integer idQueston);
}