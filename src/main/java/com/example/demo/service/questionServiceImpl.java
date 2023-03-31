package com.example.demo.service;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.dto.mapperDto.mapperDto;
import com.example.demo.dto.questionDTO;
import com.example.demo.dto.responseDto.questionResponseDto;
import com.example.demo.repository.answerRepository;
import com.example.demo.repository.categoryRepository;
import com.example.demo.repository.usersRepository;
import mapperDTO.mapperEntityAndDTO;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.question;
import com.example.demo.repository.questionRepository;
import org.springframework.stereotype.Service;


@Service
public class questionServiceImpl implements questionService {

	private questionRepository questionRepository;
	private answerRepository answerRepository;
	private categoryRepository categoryRepository;
	private usersRepository userRepository;

	public questionServiceImpl(questionRepository questionRepository, answerRepository answerRepository,
							   categoryRepository categoryRepository, usersRepository usersRepository) {
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
		this.categoryRepository = categoryRepository;
		this.userRepository = usersRepository;
	}

	@Autowired
	private userService userService;

	public List<question> questionAllList = new ArrayList<>();
	private Optional<question> getPregunta = null;
	private boolean repeatedQuestion = false; //validar pregunta repetida
	private Integer valueMinimum = 0, idCategory = 0, categorySelected = 0;
	private Integer pointQuestion = 0, scoreUser = 0;

	public Integer generarNumeroAleatorio() {
		//Integer numeroAleatorio = random.nextInt((questionRepository.findAll().size()-1) + 1) + 1;
		List<question> listQuestion = questionRepository.findByCategoryId(idCategory);

		for (question q : listQuestion) {
			if(valueMinimum == 0) {
				valueMinimum = q.getIdQuestion();
			}
		}

		Random random = new Random();
		Integer numeroAleatorio = valueMinimum + random.nextInt((valueMinimum+4) - valueMinimum + 1);
		return numeroAleatorio;
	}

	// obetener la pregunta por id con la excepcion que si no encuentra el id, salga una exception
	@Override
	public question getQuestionId(Integer questionId) {
		return questionRepository.findById(questionId).orElseThrow(() ->
				new IllegalArgumentException("pregunta con el id " +questionId + " no fue encontrada"));
	}

	@Override
	public questionResponseDto getQuestionById() {
		question question = getQuestionId(generarNumeroAleatorio());
		return mapperDto.addDataQuestionResponseDto(question);
	}

	public Optional<question> getQuestionByIdCategory(Integer idQuestionRandom, Integer categoryId) {
		List<question> listQuestion = questionRepository.findByCategoryId(categoryId);
		Optional<question> optionalQuestion = null;

		if (categoryId <= categoryRepository.count()) {
			for (question q : listQuestion) {

				// se busca el id que sea igual al del numero randon
				if (q.getIdQuestion() == idQuestionRandom) {
					optionalQuestion = Optional.of(q);
					System.out.println(q.getIdQuestion());
				}
			}
		} else {
			throw new RuntimeException("La categoria seleccionada es invalida");
		}
		return optionalQuestion;
	}

	@Override
	public List<questionResponseDto> getQuestion(Integer categoryId) {
		getPregunta = null;
		idCategory = categoryId;
		List<question> listaGetQuestion = new ArrayList<>();
		int contador = 0;

		do {
			if(categorySelected == 0) {
				categorySelected = categoryId;
			}
			if(contador > 0) {
				categorySelected = categoryId;
				valueMinimum = 0;
				questionAllList = new ArrayList<>(); // se reinicia la partida
				System.out.println("Sin nivelar " + categorySelected + " category :" +categoryId);
				System.out.println("Con nivelar " + categorySelected + " category :" +categoryId);
			}

			// categoria actual
			if(categorySelected == categoryId){
				if(questionAllList.size() < questionRepository.findByCategoryId(categoryId).size()) { // 5
					do {
						Integer idRandom = generarNumeroAleatorio();
						getPregunta = getQuestionByIdCategory(idRandom, categoryId);

						listaGetQuestion = Collections.singletonList(getPregunta.get());
						repeatedQuestion = false;

						if (questionAllList.isEmpty()) {
							questionAllList.add(getPregunta.get());
						} else {
							/* esta agregara preguntas no repetida a la lista, este tiene que ser falso para que agregue*/
							if(!buscarPreguntaRepetida(getPregunta)) questionAllList.add(getPregunta.get());
						}

					} while (repeatedQuestion);
				}

				if(repeatedQuestion) throw new RuntimeException("Pregunta repetida");

				if(getPregunta == null) {
					// questionAllList = new ArrayList<>(); // se reinicia la partida
					userService.addScoreUser(scoreUser);
					throw new RuntimeException("Nivel superado!");
				}
			}
			contador++;
		} while (categorySelected != categoryId); // idC2 == 0 || idC2 == categoryId   idC2 != 0 && idC2 != categoryId

		getPointQuestion(listaGetQuestion);

		return mapperDto.questionResponseDTOList(listaGetQuestion);
	}

	// obtener los puntos de cada pregunta
	private Integer getPointQuestion(List<question> questionList) {
		pointQuestion = 0;
		questionList.stream().forEach(x -> {
			pointQuestion = x.getPoints();
		});
		return pointQuestion;
	}

	// Sumar los puntos de las preguntas al punteje del usuario
	@Override
	public Integer valuePointQuestion() {
		scoreUser += pointQuestion;
		return scoreUser;
	}

	@Override
	public boolean validateCorrectQuestion(boolean selectedQuestion) {
		if(selectedQuestion) {
			return true;
		}
		return false;
	}

	@Override
	public questionResponseDto addQuestion(questionDTO questionDTO) {
		question question = new question();
		question.setQuestion(questionDTO.getQuestion());
		questionRepository.save(question);
		return mapperDto.addDataQuestionResponseDto(question);
	}

	@Override
	public List<questionResponseDto> getAllQuestion() {
		List<question> questionList = questionRepository.findAll();
		return mapperDto.questionResponseDTOList(questionList);
	}

	public boolean buscarPreguntaRepetida(Optional<question> getPregunta) {
		questionAllList.stream()
				.forEach(i -> {
					if(i.getIdQuestion() == getPregunta.get().getIdQuestion()) {
						repeatedQuestion = true;
					}
				});
		return repeatedQuestion;
	}

	@Override
	public List<questionDTO> getAllQuestionModelMapper() {
		List<question> questions = questionRepository.findAll();
		mapperEntityAndDTO modelMapper = new mapperEntityAndDTO();
		// se recorre la lista que contiene la informacion de la entity, para pasarlo al DTO
		List<questionDTO> questionDTOList = questions.stream()
				.map(param -> modelMapper.questionEntityConvertirDTO(param))
				.collect(Collectors.toList());
		return questionDTOList;
	}
}