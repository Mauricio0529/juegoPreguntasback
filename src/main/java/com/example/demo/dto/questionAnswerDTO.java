package com.example.demo.dto;

import com.example.demo.models.answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class questionAnswerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*  ESTE NO LO ESTOY USANDO
    * ESTE ES EL MISMO DEL questionResponseDto
    * */

    private Integer idQuestion;
    private String question; // colocar el objecto de questionDTO, no una variable string
    private List<answerDTO> OpcionesRespuestas; //

    //private String asnwer;
}

/*
https://www.youtube.com/watch?v=64CjU6xNdx4&list=PLi_K_8CU7k80q16PgYYaVazBCeBEPngLq&index=3
//////
https://www.youtube.com/watch?v=2X50sKeBAcQ
https://openwebinars.net/blog/que-es-solid/
https://www.youtube.com/watch?v=opT4C_pFgQU

relacion OneAndMany
https://www.youtube.com/watch?v=8NOMAxH2eFs
https://www.youtube.com/watch?v=Dq0HmGxxGmI

https://www.youtube.com/watch?v=dJaY43Butm8
https://www.youtube.com/watch?v=KNHpwG-n4dI - Relaciones entre tablas y entidades

https://www.youtube.com/watch?v=z_dLYcQqSHI

https://www.youtube.com/watch?v=WgMlncue6hA

 */