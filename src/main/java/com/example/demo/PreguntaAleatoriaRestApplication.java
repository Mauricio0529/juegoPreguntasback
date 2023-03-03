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
public class PreguntaAleatoriaRestApplication  { // implements CommandLineRunner

	// static Gson gsonConverter = new GsonBuilder().setPrettyPrinting().create();

	// instanciamos el mapper del dto
	//static mapperEntityAndDTO modelMapper = mapperEntityAndDTO.singleInstance();

/*	@Autowired
	private questionRepository questionRespository;

	@Autowired
	private categoryRepository categoryRepository;

	@Autowired
	private answerRepository answerRepository;*/

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

	//@Override
	//public void run(String... args) throws Exception {
		/* category category = new category(4, "Cultura General", true );

		question questionA = new question(3, "Cuantos departamentos tiene Colombia");

		question questionB = new question(4, "Cuantos paises existen en el mundo");
		*/

		/*
			answer optionsAnswerA = new answer(21, "V", true);
			answer optionsAnswerB = new answer(22, "X", false);
			answer optionsAnswerC = new answer(23, "|||", false);
			answer optionsAnswerD = new answer(24, "XX", false);
		*/

	/*	List<question> questionList = new ArrayList<>();
		questionA.setCategory(category);
		questionB.setCategory(category);

		questionList.add(questionA);
		questionList.add(questionB);

		category.setQuestionList(questionList);

		categoryRepository.save(category);*/

	//}


// https://www.youtube.com/watch?v=G0Zc6wN7tWA&t=381s video largo, TIENE PARA HACER JUNIT TEST
// https://www.youtube.com/watch?v=WKCD11lSh8U&list=PLub7kY4x2lvFNeqbqzlLqH8AC1ON670Hf&index=18
// https://www.youtube.com/results?search_query=como+user+el+modelmapper+spring+boot
// https://javadesde0.com/ejemplo-de-mapeo-de-entidades-con-modelmapper/
// https://www.youtube.com/watch?v=7ryyEjt2RDw - Swagger
// https://www.youtube.com/watch?v=gT3h3L0O4SM&list=PLi_K_8CU7k80q16PgYYaVazBCeBEPngLq
// https://www.youtube.com/watch?v=ZY4mUro6Kd0

}