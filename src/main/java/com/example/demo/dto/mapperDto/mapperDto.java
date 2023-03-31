package com.example.demo.dto.mapperDto;

import com.example.demo.dto.responseDto.answerResponseDto;
import com.example.demo.dto.responseDto.categoryResponseDto;
import com.example.demo.dto.responseDto.questionResponseDto;
import com.example.demo.dto.responseDto.userScoreDto;
import com.example.demo.models.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component("mapperDto")
public class mapperDto {

    private static ModelMapper modelMapper = new ModelMapper();

    public static answerResponseDto answerResponseDto(answer answerEntity) {

        answerResponseDto answerResponseDto = new answerResponseDto();
        answerResponseDto.setId(answerEntity.getIdAnswer());
        answerResponseDto.setAsnwerName(answerEntity.getOptionAnswer());
        answerResponseDto.setCorrectAnswer(answerEntity.getCorrectAnswer());

        return answerResponseDto;
    }

    public static List<answerResponseDto> answerResponseDtoList(List<answer> answerList) {
        List<answerResponseDto> answerDto = new ArrayList<>();
        for (answer answer: answerList) {
            answerDto.add(mapperDto.answerResponseDto(answer));
        }
        return answerDto;
    }

    public static questionResponseDto addDataQuestionResponseDto(question questionEntity) {

        questionResponseDto questionResponseDto = new questionResponseDto();
        questionResponseDto.setId(questionEntity.getIdQuestion());
        questionResponseDto.setNameQuestion(questionEntity.getQuestion());

        List<answerResponseDto> answerList = new ArrayList<>();
        List<answer> answersObject = questionEntity.getOptionsAnswerList();

        for (answer answer : answersObject) {
            answerList.add(answerResponseDto(answer));
        }
        questionResponseDto.setAnswerName(answerList);

        return questionResponseDto;
    }

    // modelMapper
    public static questionResponseDto questionEntityConvertirDTO(question questionEntity) {
        questionResponseDto questionResponseDto = new questionResponseDto();
        if (questionEntity != null) {
            // usamos el modelMapper para agregar la informacion de la entity al dto.
            questionResponseDto = modelMapper.map(questionEntity, questionResponseDto.class);
        }
        return questionResponseDto;
    }

    public static List<questionResponseDto> questionResponseDTOList(List<question> questionList) {
        List<questionResponseDto> questionResponseDtoList = new ArrayList<>();

        for (question question: questionList){
            questionResponseDtoList.add(addDataQuestionResponseDto(question));
        }

        return questionResponseDtoList;
    }

    public static categoryResponseDto categoryEntityToDto(category categoryEntity) {
        categoryResponseDto categoryResponseDto = new categoryResponseDto();
        categoryResponseDto.setId(categoryEntity.getId());
        categoryResponseDto.setNameCategory(categoryEntity.getNameCategory());

        return categoryResponseDto;
    }

    public static List<categoryResponseDto> categoryResponseDTOList(List<category> categoryEntityList) {
        List<categoryResponseDto> categoryResponseDtoList = new ArrayList<>();

        for (category category: categoryEntityList) {
            categoryResponseDtoList.add(categoryEntityToDto(category));
        }
        return categoryResponseDtoList;
    }

    public static userScoreDto userEntityTouserScoreDto(user userEntity) {
        userScoreDto userScoreDto = new userScoreDto();
        userScoreDto.setName(userEntity.getUsername());
        userScoreDto.setScore(userEntity.getScore());

        Set<rolUser> userSet = userEntity.getRolUser();
        userSet.stream().forEach(x -> {
                userScoreDto.setRol(x.getRol().getNameRol());
        });

        return userScoreDto;
    }

    public static List<userScoreDto> userScoreDtoList(List<user> userEntityList) {
        List<userScoreDto> userScoreDtoList = new ArrayList<>();
        for (user userEntity: userEntityList) {
            userScoreDtoList.add(userEntityTouserScoreDto(userEntity));
        }
        return userScoreDtoList;
    }
}