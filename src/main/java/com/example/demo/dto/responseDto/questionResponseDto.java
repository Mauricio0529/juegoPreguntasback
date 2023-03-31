package com.example.demo.dto.responseDto;

import lombok.Data;

import java.util.List;

@Data
public class questionResponseDto {
    private Integer id;
    private String nameQuestion;
    private List<answerResponseDto> answerName;
}