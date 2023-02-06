package com.example.demo.dto.responseDto;

import lombok.Data;

import java.util.List;

@Data
public class questionResponseDto {
    /*
         Esta se usa cuando quiero mostrar datos, Response.
     El response seria la union de dos Dto o tablas -> question + answer = questionResponseDto
    */

    private Integer id;
    private String nameQuestion;

    /* SE COLOCA COMO STRING, POR QUE VAMOS A OBTENER SOLO EL TEXTO DE LAS RESPUESTA
    * TOCARIA devolver el dto del answer, para que tener el texto y el boolean
    * */
    private List<answerResponseDto> answerName;
}