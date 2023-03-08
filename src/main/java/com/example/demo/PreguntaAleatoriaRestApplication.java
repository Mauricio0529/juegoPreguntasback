package com.example.demo;

import com.example.demo.models.answer;
import com.example.demo.models.category;
import com.example.demo.models.question;
import com.example.demo.repository.answerRepository;
import com.example.demo.repository.categoryRepository;
import com.example.demo.repository.questionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class PreguntaAleatoriaRestApplication { // implements CommandLineRunner

	// static Gson gsonConverter = new GsonBuilder().setPrettyPrinting().create();

	// instanciamos el mapper del dto
	//static mapperEntityAndDTO modelMapper = mapperEntityAndDTO.singleInstance();

	@Autowired
	private questionRepository questionRespository;

	@Autowired
	private categoryRepository categoryRepository;

	@Autowired
	private answerRepository answerRepository;

	public static void main(String[] args) {
		SpringApplication.run(PreguntaAleatoriaRestApplication.class, args);

	//	var mapperQuestionDTO = modelMapper.entityToDTO(question);
	//	System.out.println("ModelMapper ->\n " + gsonConverter.toJson(mapperQuestionDTO));
	}

	// EL CORS PARA CONECTARSE AL FRONT DE ANGULAR
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*").allowedHeaders("*");
			}
		};
	}

	/*

	@Override
	public void run(String... args) throws Exception {
		category category = new category(4, "Cultura General", true);

		question questionA = new question(20, "¿Cuál es el planeta más grande del Sistema Solar?");
		// pasamos el objeto categoria a preguntas, para que el id de categoria se registre en la preguntas.
		questionA.setCategory(category);

		answer optionsAnswerA = new answer(77, "Marte", false);
		answer optionsAnswerB = new answer(78, "Jupiter", true);
		answer optionsAnswerC = new answer(79, "Saturno", false);
		answer optionsAnswerD = new answer(80, "Venus", false);
		// pasamos el objeto pregunta a respuestas, para que el id de pregunta se registre en la respuestas.
		optionsAnswerA.setQuestion(questionA);
		optionsAnswerB.setQuestion(questionA);
		optionsAnswerC.setQuestion(questionA);
		optionsAnswerD.setQuestion(questionA);

		// lista de preguntas
		List<question> questionList = new ArrayList<>();
		// se pasa el objeto con la pregunta y el id de categoria que se registro en el objeto questionA
		questionList.add(questionA);
		//questionList.add(questionB);

		// lista de respuestas
		List<answer> answerList = new ArrayList<>();
		// se pasa el objeto con la respuesta y el id de pregunta que se registro en el objeto optionsAnswerA...optionsAnswerD
		answerList.add(optionsAnswerA);
		answerList.add(optionsAnswerB);
		answerList.add(optionsAnswerC);
		answerList.add(optionsAnswerD);


		// se pasa la lista que tengan en la entity
		// category posee las preguntas
		category.setQuestionList(questionList);

		// question posee las respuestas
		questionA.setOptionsAnswerList(answerList);

		// se hace una sola persistencia, ya que en las relaciones coloque cascade = CascadeType.ALL,
		// si una tabla se hace un registro o es modificada las otras seran afectadas al cambio en el id de FORENKEY de las relaciones, ->
		// -> ya que poseen el id de sus relaciones.
		categoryRepository.save(category);
	}

	*/

// https://www.youtube.com/watch?v=G0Zc6wN7tWA&t=381s video largo, TIENE PARA HACER JUNIT TEST
// https://www.youtube.com/watch?v=WKCD11lSh8U&list=PLub7kY4x2lvFNeqbqzlLqH8AC1ON670Hf&index=18
// https://www.youtube.com/results?search_query=como+user+el+modelmapper+spring+boot
// https://javadesde0.com/ejemplo-de-mapeo-de-entidades-con-modelmapper/
// https://www.youtube.com/watch?v=7ryyEjt2RDw - Swagger
// https://www.youtube.com/watch?v=gT3h3L0O4SM&list=PLi_K_8CU7k80q16PgYYaVazBCeBEPngLq
// https://www.youtube.com/watch?v=ZY4mUro6Kd0

}