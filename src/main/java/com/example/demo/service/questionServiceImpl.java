package com.example.demo.service;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.dto.mapperDto.mapperDto;
import com.example.demo.dto.questionDTO;
import com.example.demo.dto.responseDto.questionResponseDto;
import com.example.demo.models.answer;
import com.example.demo.repository.answerRepository;
import com.example.demo.repository.categoryRepository;
import mapperDTO.mapperEntityAndDTO;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.question;
import com.example.demo.repository.questionRepository;
import org.springframework.stereotype.Service;

/*
 * https://www.youtube.com/watch?v=_S7pdRmLSCc
 *como buscar por otro valor que no sea por id en spring boot
 *https://www.youtube.com/watch?v=mgXmTZZCsbI
 *
 * https://www.oscarblancarteblog.com/2018/11/30/data-transfer-object-dto-patron-diseno/
 * */
@Service
public class questionServiceImpl implements questionService {

	@Autowired
	private questionRepository questionRepository;

	@Autowired
	private answerRepository answerRepository;

	@Autowired
	private categoryRepository categoryRepository;

	private List<question> questionAllList = new ArrayList<>();
	private Optional<question> getPregunta = null;
	private boolean repeatedQuestion = false; //validar pregunta repetida
	private Integer valueMinimum = 0, idCategory = 0, categorySelected = 0;

	public Integer generarNumeroAleatorio() {
		//Integer numeroAleatorio = random.nextInt((questionRepository.findAll().size()-1) + 1) + 1;
		//Integer numeroAleatorio = minimo + random.nextInt((minimo+4) - minimo + 1);
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
		// getPregunta = null;
		Optional<question> optionalQuestion = null;

		if (categoryId <= categoryRepository.count()) {
			for (question q : listQuestion) {
				// se busca el id que sea igual al del numero randon
				if (q.getIdQuestion() == idQuestionRandom) {
					optionalQuestion = Optional.of(q);
					//getPregunta = Optional.ofNullable(q);
					System.out.println(q.getIdQuestion());
				}
			}
		} else {
			throw new RuntimeException("La categoria seleccionada es invalida");
		}
		return optionalQuestion;
	}

	@Override
	public List<questionResponseDto> getQuestionByCategory(Integer categoryId) {
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
				System.out.println("Sin nivelar " + categorySelected + " category :" +categoryId);
				System.out.println("Con nivelar " + categorySelected + " category :" +categoryId);
			}
			if(categorySelected == categoryId){
				// normal
				if(questionAllList.size() < questionRepository.findByCategoryId(categoryId).size()) { // 5
					do {
						Integer idRandom = generarNumeroAleatorio();
						System.out.println("El number aleatorio es : ->>" + idRandom);
						//getPregunta =  questionRepository.findById(idRandom);
						getPregunta = getQuestionByIdCategory(idRandom, categoryId);
						//getPregunta = Optional.ofNullable(getQuestionByC(idRandom));

						listaGetQuestion = Collections.singletonList(getPregunta.get());// para el front toca con el list
						repeatedQuestion = false;

						if (questionAllList.isEmpty()) {
							questionAllList.add(getPregunta.get());
						} else {
							/* esta agregara preguntas no repetida a la lista, este tiene que ser falso para que agregue*/
							if(!buscarPreguntaRepetida(getPregunta)) questionAllList.add(getPregunta.get());
						}

						System.out.println(" ");
						System.out.println("*** Prueba ****");

						questionAllList.stream()
								.forEach((e) -> {
									System.out.println("ID: "+e.getIdQuestion() + ", Pregunta: "+ e.getQuestion());
								});

					} while (repeatedQuestion);
				}
				System.out.println("Dato "+ repeatedQuestion);

				if(repeatedQuestion) throw new RuntimeException("Pregunta repetida");

				if(getPregunta == null) {
					questionAllList = new ArrayList<>();
					throw new RuntimeException("Nivel superado!");
				}
			}
			contador++;
		} while (categorySelected != categoryId); // idC2 == 0 || idC2 == categoryId   idC2 != 0 && idC2 != categoryId

		// se agrega la informacion a la lista del dto
		return mapperDto.questionResponseDTOList(listaGetQuestion);
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
		// 2:10:16
		List<question> questionList = questionRepository.findAll();
		return mapperDto.questionResponseDTOList(questionList);
	}


	// se coloca list para retornarlo mas facil en el front, ya que con el Optional es distinto
	public List<questionResponseDto> getQuestion() {
		getPregunta = null;
		List<question> listaGetQuestion = null;
		List<questionResponseDto> listaQuestionDto = null;

		if(questionAllList.size() < questionRepository.count()){
			do {
				Integer idRandom = generarNumeroAleatorio();
				getPregunta =  questionRepository.findById(idRandom);
				listaGetQuestion = Collections.singletonList(getPregunta.get());// para el front toca con el list
				repeatedQuestion = false;

				if (questionAllList.isEmpty()) {
					questionAllList.add(getPregunta.get());
				} else {
					/* esta agregara preguntas no repetida a la lista, este tiene que ser falso para que agregue*/
					if(!buscarPreguntaRepetida(getPregunta)) questionAllList.add(getPregunta.get());
				}

				System.out.println(" ");
				System.out.println("*** Prueba ****");

				questionAllList.stream()
							.forEach((e) -> {
								System.out.println("ID: "+e.getIdQuestion() + ", Pregunta: "+ e.getQuestion());
							});

			} while (repeatedQuestion);
		}

		System.out.println("Dato "+ repeatedQuestion);

		/*
		pregunta repetida, en lugar de retornar una lista como mensaje.
		seria mejor retornar una exception.
	 	este mensaje no se mostraria al usuario.
		*/
		if(repeatedQuestion) throw new RuntimeException("Pregunta repetida");

		if(getPregunta == null) throw new RuntimeException("Nivel superado!");

		// se agrega la informacion a la lista del dto
		return mapperDto.questionResponseDTOList(listaGetQuestion);
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

	// este lo puedo hacer con throws exception
	public List<questionDTO> mensajeRespuestaValorRepetido(String msg) {
		List<questionDTO> mensaje = new ArrayList<>();
		mensaje.add(new questionDTO(msg));
		//Optional<question> respuestaValorRepetido = Optional.ofNullable(mensaje.get(0));
		return mensaje;
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

	// con modelMapper
	public List<questionDTO> pregunta() {
		mapperEntityAndDTO mapperEntityAndDTO = new mapperEntityAndDTO();
		List<question> Listquestion = null;
		List<questionDTO> ListquestionDTO = null;
		Optional<question> preguntaId = questionRepository.findById(generarNumeroAleatorio());

		Listquestion = Collections.singletonList(preguntaId.get());

		return ListquestionDTO = Listquestion.stream().map(param -> mapperEntityAndDTO.questionEntityConvertirDTO(param)).collect(Collectors.toList());
	}

	// con modelMapper
	public List<answer> answers() {
		/*
		 * NOTA: NO DEBO RETORNAR LA LISTA QUE TENGA LA PERSISTENCIA FINDALL, YA QUE TRAE TODA LA INFORMACION DE LA BD
		 *
		 * NOTA: SOLO SE HACE UN GET PARA OBTENER DATOS DE question y answer.
		 * ES MALA PRACTICA ENVIAR DOS EDPOINT QUE TENGAN RELACION A UNOS DATOS REQUERIDOS.
		 * EJ: QUIERO MOSTRAR LA PREGUNTA Y LA RESPUESTA EN ESE CASO SE ENVIA UN ENDPOINT CON LA DATA REQUERIDA.
		 * NO SE ENVIA DOS ENDPOINT CON LA DATA.(YO PENSABA QUE CON LAS DOS SE PODIA UNIR EN EL FRONT) ESTO ES MALA PRACTICA
		 * Al momento de enviar los edpoint, se envia un edpoint con la data que se necesita, con la question y answer.
		 *
		 * */

		List<answer> answerList = answerRepository.findAll();
		Optional<question> listaPregunta = questionRepository.findById(generarNumeroAleatorio());

		List<answer> as = answerList.stream()
				.filter(x -> x.getQuestion().getIdQuestion() == listaPregunta.get().getIdQuestion())
				.collect(Collectors.toList());

		as.stream().forEach(x -> {
			System.out.println("Pregunta -> " + x.getQuestion().getIdQuestion());
		});
/*
PRINCIPIOS DE SOLID
* https://www.youtube.com/watch?v=2X50sKeBAcQ
* https://openwebinars.net/blog/que-es-solid/
* https://www.youtube.com/watch?v=opT4C_pFgQU
*
* */
		return as;
	}

	static boolean buscarPreguntaRepetida1(int idPregunta, Optional<question> getPregunta) {
		return  idPregunta == getPregunta.get().getIdQuestion();
	}
}
// https://arc.dev/
// workana y fiverr freelance