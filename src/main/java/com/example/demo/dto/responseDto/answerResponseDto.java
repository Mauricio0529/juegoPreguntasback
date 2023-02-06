package com.example.demo.dto.responseDto;

import lombok.Data;


@Data
public class answerResponseDto {

    /*
       Esta se usa cuando quiero mostrar datos, Response.
        El response seria la union de dos Dto o tablas.
    */
    private Integer id;
    private String asnwerName;
    private Boolean correctAnswer;
    /* Se devolvera o obtendra un string del texto de la pregunta */
    // private String questionName;
}