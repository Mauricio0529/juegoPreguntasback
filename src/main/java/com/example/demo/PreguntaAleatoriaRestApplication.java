package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PreguntaAleatoriaRestApplication { // implements CommandLineRunner

	public static void main(String[] args) {
		SpringApplication.run(PreguntaAleatoriaRestApplication.class, args);
	}

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
		questionA.setCategory(category);

		answer optionsAnswerA = new answer(77, "Marte", false);
		answer optionsAnswerB = new answer(78, "Jupiter", true);
		answer optionsAnswerC = new answer(79, "Saturno", false);
		answer optionsAnswerD = new answer(80, "Venus", false);

		optionsAnswerA.setQuestion(questionA);
		optionsAnswerB.setQuestion(questionA);
		optionsAnswerC.setQuestion(questionA);
		optionsAnswerD.setQuestion(questionA);

		// lista de preguntas
		List<question> questionList = new ArrayList<>();
		questionList.add(questionA);

		// lista de respuestas
		List<answer> answerList = new ArrayList<>();
		answerList.add(optionsAnswerA);
		answerList.add(optionsAnswerB);
		answerList.add(optionsAnswerC);
		answerList.add(optionsAnswerD);

		category.setQuestionList(questionList);

		questionA.setOptionsAnswerList(answerList);

		categoryRepository.save(category);
	}

	*/
}