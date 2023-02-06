package com.example.demo.service;

import com.example.demo.dto.answerDTO;
import com.example.demo.dto.mapperDto.mapperDto;
import com.example.demo.dto.responseDto.answerResponseDto;
import com.example.demo.models.answer;
import com.example.demo.models.question;
import com.example.demo.repository.answerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class answerServiceImpl implements answerService {

    @Autowired
    private answerRepository answerRepository;

    @Autowired
    private questionService questionService;

    @Transactional
    @Override
    public answerResponseDto addAnswer(answerDTO answerDTO) {
        answer answerEntity = new answer();
        answerEntity.setOptionAnswer(answerDTO.getOptionAnswer());
        answerEntity.setCorrectAnswer(answerDTO.getCorrectAnswer());

        /*
            Validamos si la peticion nos trae la pregunta.
            por que una respuesta tiene al menos una pregunta
            https://www.youtube.com/watch?v=oMpbjRBDf8A&t=3964s
            https://www.youtube.com/watch?v=eqwzM9Xny2s
        */
        if(answerDTO.getQuestionId() == null) {
            throw new IllegalArgumentException("No contiene la pregunta");
        }

        // obtenemos el id de pregunta por medio de la clase servicio question
        question question = questionService.getQuestionId(answerDTO.getQuestionId());
        answerEntity.setQuestion(question);

        answer answerEntity1 = answerRepository.save(answerEntity);
        return mapperDto.answerResponseDto(answerEntity1);
    }

    @Override
    public answer getAnswerById(Integer id) {
        answer answer = answerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("El id del Answer: " + id + " No fue encontrado"));
        return answer;
    }

    @Override
    public answerResponseDto getAnswer(Integer idAnswer) {
        answer answer = getAnswerById(idAnswer);
        return mapperDto.answerResponseDto(answer);
    }

    @Override
    public Boolean validateAnswerCorrect(Integer idAnswerSelect) {
        answerResponseDto answerResponseDto = getAnswer(idAnswerSelect);
        return answerResponseDto.getCorrectAnswer() == true;
    }

    // Mio
    @Override
    public answerResponseDto getAnswerByQuestionId(Integer idQuestion) {
        answer answer = getIdQuestionToAnswer(idQuestion);
        return mapperDto.answerResponseDto(answer);
    }

    // Mio
    @Override
    public answer getIdQuestionToAnswer(Integer idQuestion) {
        answer answer = answerRepository.findByQuestion(idQuestion);
        if (!Objects.nonNull(answer)) {
            System.out.println(" * Excepcion => " + answer);
            throw new RuntimeException("Objeto vacio ");
        }
        return answer;
    }

    @Transactional
    @Override
    public answerResponseDto updateAnswer(Integer id, answerDTO answerDTO) {
        answer answerToUpdate = getAnswerById(id);
        answerToUpdate.setOptionAnswer(answerDTO.getOptionAnswer());
        answerToUpdate.setCorrectAnswer(answerDTO.getCorrectAnswer());

        // VALIDAMIS si la respuesta posee una pregunta
        if(answerDTO.getQuestionId() != null) {
            question question = questionService.getQuestionId(answerDTO.getQuestionId());
            // AGREGAMOS EL OBJETO EN LA ENTITY
            answerToUpdate.setQuestion(question);
        }
        //answer answer1 = answerRepository.save(answerToUpdate);
        return mapperDto.answerResponseDto(answerToUpdate);
    }

    @Override
    public answerResponseDto deleteAnswer(Integer id, answerDTO answerDTO) {
        answer answer = getAnswerById(id);
        answerRepository.delete(answer);
        return mapperDto.answerResponseDto(answer);
    }

    @Override
    public List<answerResponseDto> getAllAnswer() {
        List<answerResponseDto> answerDto = new ArrayList<>();
        List<answer> answer = answerRepository.findAll();

        for (answer a: answer) {
            answerDto.add(mapperDto.answerResponseDto(a));
        }
        // 1:48:00 minuto
        return answerDto;
    }

    // AGREGAR UNA PREGUNTA A RESPUESTA MINUTO 1:53:38
    @Override
    public answerResponseDto addQuestionToAnswer(Integer idQuestionToAnswer, Integer idQueston) {
        return null;
    }

    @Override
    public answerResponseDto deleteQuestionToAnswer(Integer idQuestionToAnswer, Integer idQueston) {
        return null;
    }

    @Override
    public answerResponseDto updateQuestionToAnswer(Integer idQuestionToAnswer, Integer idQueston) {
        return null;
    }
}