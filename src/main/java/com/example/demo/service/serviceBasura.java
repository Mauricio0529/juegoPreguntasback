package com.example.demo.service;


import java.util.*;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.example.demo.models.question;
import com.example.demo.repository.questionRepository;
import org.springframework.stereotype.Service;

//@Service
public class serviceBasura {
/*
    @Autowired
    private questionRepository questionRepository;
    List<Integer> preguntasHechas = new ArrayList<>();
    int i = 0;

    public Integer getQuestionAleatorio(){
        Random random = new Random();
        int pregunta = random.nextInt((questionRepository.findAll().size()-1) + 1) + 1;
        return pregunta;
    }

    public List<Integer> getQuestionAleatorioTexto2() {
        List<Integer> preguntasHechas2 = new ArrayList<>();
        int j = 0;
        while (j < 5){
            preguntasHechas.add(i);
            System.out.println(preguntasHechas.get(i));
            i++;
            j++;
        }
        return preguntasHechas;
    }


    public List<question> getQuestionAleatorioTexto() {
        Random random = new Random();
        Integer idPregunta = random.nextInt((questionRepository.findAll().size() - 1) + 1) + 1;
        List<question> nombrePregunta = questionRepository.findAllById(Collections.singleton(idPregunta));

        for (question e: nombrePregunta){

            if (preguntasHechas.size() == 0){
                preguntasHechas.add(e.getIdQuestion());
                System.out.println(preguntasHechas);
                System.out.println(preguntasHechas.size());
            } else {
                for (int k = 0; k < preguntasHechas.size(); k++){
                    if(preguntasHechas.get(k) == idPregunta){
                        System.out.println("Esta pregunta ya se repitio");
                    } else {
                        System.out.println("sigue");
                        preguntasHechas.add(e.getIdQuestion());
                        //preguntasHechas.add(nombrePregunta.get(k).getIdQuestion());
                        System.out.println(preguntasHechas);
                        System.out.println(preguntasHechas.size());
                    }
                }
            }

*/
		/*	preguntasHechas.add(e.getIdQuestion());
			System.out.println(preguntasHechas);
			System.out.println(preguntasHechas.size());*/
     //   }



	/*	for (int i = 0; i < nombrePregunta.size(); i++){
			preguntasHechas.add(nombrePregunta.get(i));
		}

		for (int i = 0; i < nombrePregunta.size(); i++){
			//preguntasHechas = nombrePregunta;
			//System.out.println(preguntasHechas.get(i).getQuestion());

			if(preguntasHechas.size() == nombrePregunta.size()){
				System.out.println("**** Preguntas completas " + preguntasHechas.size());
			} else {
				//preguntasHechas.add(q);
				System.out.println("N. " + preguntasHechas.size());
			}

		}

		for (question q: nombrePregunta) {
		}

		for (int i = 0; i < preguntasHechas.size(); i++){
			System.out.println(preguntasHechas.get(i).getQuestion());
		}
		*/
// https://es.stackoverflow.com/questions/387613/agregar-objetos-a-una-lista-en-un-bucle-java
        //return nombrePregunta;
  //  }
/*
    @Override
    public List<question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public List<question> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<question> findAllById(Iterable<Integer> ids) {
        return null;
    }

    @Override
    public <S extends com.example.demo.models.question> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub

    }

    @Override
    public <S extends com.example.demo.models.question> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends com.example.demo.models.question> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<question> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> ids) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub

    }

    @Override
    public question getOne(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public question getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public question getReferenceById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends com.example.demo.models.question> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends com.example.demo.models.question> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<question> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends com.example.demo.models.question> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<question> findById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(question entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll(Iterable<? extends question> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public <S extends com.example.demo.models.question> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends com.example.demo.models.question> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends com.example.demo.models.question> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends com.example.demo.models.question> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends com.example.demo.models.question, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }
    */
}