package com.example.demo.dto.mapperDto;

import com.example.demo.dto.responseDto.answerOptionDto;
import com.example.demo.dto.responseDto.answerResponseDto;
import com.example.demo.dto.responseDto.questionResponseDto;
import com.example.demo.models.answer;
import com.example.demo.models.question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("mapperDto")
public class mapperDto {
 /*
 * se mappea para las respuesta(response) del dto
 *
 * */

    private static ModelMapper modelMapper = new ModelMapper();

    // PASAR O MAPEAR UNA ENTITY A UN DTO
    // CREERIA QUE AL PASAR LA CLASE, SE PASARIA LOS CAMPOS QUE TIENE LA TABLA
    public static answerResponseDto answerResponseDto(answer answerEntity) {
        answerResponseDto answerResponseDto = new answerResponseDto();
        // registramos datos a la clase DTO
        answerResponseDto.setId(answerEntity.getIdAnswer());
        answerResponseDto.setAsnwerName(answerEntity.getOptionAnswer());
        answerResponseDto.setCorrectAnswer(answerEntity.getCorrectAnswer());

        // establecemos el nombre o texto del question, se obtiene el texto de la question
        // answerResponseDto.setQuestionName(answerEntity.getQuestion().getQuestion());

        // o en lugar de obtener el texto, se obtiene el id para buscar las respuestas
        //answerResponseDto.setQuestionIdNumber(answerEntity.getQuestion().getIdQuestion());

        return answerResponseDto;
    }

    public static answerOptionDto answerOptionDto(answer answer) {
        answerOptionDto answerOptionDto = new answerOptionDto();

        answerOptionDto.setId(answer.getIdAnswer());
        answerOptionDto.setNameAnswer(answer.getOptionAnswer());
        return answerOptionDto;
    }

    public static List<answerResponseDto> answerResponseDtoList(List<answer> answerList) {
        List<answerResponseDto> answerDto = new ArrayList<>();
        for (answer answer: answerList) {
            answerDto.add(mapperDto.answerResponseDto(answer));
        }
        return answerDto;
    }

    // mappear question paso 1
    // pasar o mapear un entity a dto
    // agregamos informacion al objecto questionResponseDto
    public static questionResponseDto addDataQuestionResponseDto(question questionEntity) {
        questionResponseDto questionResponseDto = new questionResponseDto();
        questionResponseDto.setId(questionEntity.getIdQuestion()); // campo ID
        questionResponseDto.setNameQuestion(questionEntity.getQuestion()); // campo question

        // se hace esto ya que la clase question y questionResponseDto poseen un List
        /*
        NOTA: SI QUIERO QUE APAREZCA EL BOOLEN EN LA LISTA DE ANSWER, TOCARIA EN LUGAR DE STRING SERIA LA CLASE answerDTO
        Y ESTA LISTA PEDIRIA UN OBJETO AL MOMENTO DE AGREGAR
        */
        List<answerResponseDto> txtAnswerList = new ArrayList<>(); // en lugar de que sea texto, se colocaria la clase answerDTO
        List<answer> answersObject = questionEntity.getOptionsAnswerList(); // se obtiene la info de la lista de la base de datos

        // recorremos el answersObject que contiene informacion y agregamos los nombres a la lista de string, txtAnswerList
        for (answer a: answersObject) {
            txtAnswerList.add(answerResponseDto(a)); // se pasa un get y no una lista ni un objeto si no un texto por que solo es un dato que es texto
        }
        // pasamos la lista con el texto obtenido hacia la lista que contiene el DTO
        questionResponseDto.setAnswerName(txtAnswerList); // campos List answerName

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

    // lista con pregunta y respuesta esta es para mappear el question paso 2
    // pasar o mapear una lista ENTITY a una lista DTO
    /* por que se crea este metodoList?, por que al momento de mapear un dto que contenga un List(depende de la relacion entre tablas)
    * en este caso es @OneToMany uno a muchos, entonces una pregunta contiene multiples respuesta(una categoria puede contener muchos productos)
    * */
    public static List<questionResponseDto> questionResponseDTOList(List<question> questionList) {
        List<questionResponseDto> questionResponseDtoList = new ArrayList<>();
        /*
            SE COLOCA question, por que estamos mapeando la clase question.
            Se pasa la lista questionList ya que con esa se pasaria las preguntas en una lista
             y esas preguntas se agregaria a la lista questionResponseDtoList
        */
        //se coloca question ya que contiene la lista de answer en la clase entity
        for (question question: questionList){ // recorremos la lista questionList

            // AQUI INSTANCIAMOS AL METODO QUE MAPEA O PASA UN ENTITY A DTO
            // Y LE PASAMOS EL OBJETO QUE RECORRE LA LISTA QUE PASAMOS EN LA SERVICIO
            questionResponseDtoList.add(addDataQuestionResponseDto(question)); // aqui pide ya un objetoDTO, entonces usamos el metodo que mapeamos el question
        }
        return questionResponseDtoList;
    }

}

// https://www.youtube.com/watch?v=oMpbjRBDf8A&t=3651s



/*
 public static questionResponseDto addDataQuestionResponseDto(question questionEntity) {
        questionResponseDto questionResponseDto = new questionResponseDto();
        questionResponseDto.setId(questionEntity.getIdQuestion()); // campo ID
        questionResponseDto.setNameQuestion(questionEntity.getQuestion()); // campo question

        // se hace esto ya que la clase question y questionResponseDto poseen un List

     //   NOTA: SI QUIERO QUE APAREZCA EL BOOLEN EN LA LISTA DE ANSWER, TOCARIA EN LUGAR DE STRING SERIA LA CLASE answerDTO
     //   Y ESTA LISTA PEDIRIA UN OBJETO AL MOMENTO DE AGREGAR

List<String> txtAnswerList = new ArrayList<>(); // en lugar de que sea texto, se colocaria la clase answerDTO
    List<answer> answersObject = questionEntity.getOptionsAnswerList(); // se obtiene la info de la lista de la base de datos

// recorremos el answersObject que contiene informacion y agregamos los nombres a la lista de string, txtAnswerList
        for (answer a: answersObject) {
                txtAnswerList.add(a.getOptionAnswer()); // se pasa un get y no una lista ni un objeto si no un texto por que solo es un dato que es texto
                }
                // pasamos la lista con el texto obtenido hacia la lista que contiene el DTO
                questionResponseDto.setAnswerName(txtAnswerList); // campos List answerName

                return questionResponseDto;
                }
 */