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

    private answerRepository answerRepository;

    public answerServiceImpl(answerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Autowired
    private questionService questionService;

    @Transactional
    @Override
    public answerResponseDto addAnswer(answerDTO answerDTO) {
        answer answerEntity = new answer();
        answerEntity.setOptionAnswer(answerDTO.getOptionAnswer());
        answerEntity.setCorrectAnswer(answerDTO.getCorrectAnswer());

        if(answerDTO.getQuestionId() == null) {
            throw new IllegalArgumentException("No contiene la pregunta");
        }

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

    //
    @Override
    public answerResponseDto getAnswerByQuestionId(Integer idQuestion) {
        answer answer = getIdQuestionToAnswer(idQuestion);
        return mapperDto.answerResponseDto(answer);
    }

    //
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

        // si la respuesta posee una pregunta
        if(answerDTO.getQuestionId() != null) {
            question question = questionService.getQuestionId(answerDTO.getQuestionId());
            answerToUpdate.setQuestion(question);
        }
        answerRepository.save(answerToUpdate);
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
        return answerDto;
    }

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