package com.example.demo;

import com.example.demo.repository.answerRepository;
import com.example.demo.repository.questionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PreguntaAleatoriaRestApplication {

	// static Gson gsonConverter = new GsonBuilder().setPrettyPrinting().create();

	// instanciamos el mapper del dto
	//static mapperEntityAndDTO modelMapper = mapperEntityAndDTO.singleInstance();

	@Autowired
	private questionRepository questionRespository;

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

// https://www.youtube.com/watch?v=G0Zc6wN7tWA&t=381s video largo, TIENE PARA HACER JUNIT TEST
// https://www.youtube.com/watch?v=WKCD11lSh8U&list=PLub7kY4x2lvFNeqbqzlLqH8AC1ON670Hf&index=18
// https://www.youtube.com/results?search_query=como+user+el+modelmapper+spring+boot
// https://javadesde0.com/ejemplo-de-mapeo-de-entidades-con-modelmapper/
// https://www.youtube.com/watch?v=7ryyEjt2RDw - Swagger
// https://www.youtube.com/watch?v=gT3h3L0O4SM&list=PLi_K_8CU7k80q16PgYYaVazBCeBEPngLq
// https://www.youtube.com/watch?v=ZY4mUro6Kd0

}