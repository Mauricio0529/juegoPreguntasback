package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class questionDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    /*
         Esta se usa cuando quiero agregar datos, Request.
         ESTE DTO ES EL QUE VA A PEDIR O REGISTRAR DATOS
    */

    // private Integer idQuestion;
    private String question;
}