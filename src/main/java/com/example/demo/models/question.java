package com.example.demo.models;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "question")
public class question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idQuestion;

	@Column(name = "question")
	private String question;

	@Column(name = "points")
	private Integer points = 1;

	// ** fetch = FetchType.LAZY, al momento de hacer una peticion a esta tabla,
	// no se mostraran o consumira esta variable, esta se usara cuando la instanciemos o llamemos
	// ** cascade = CascadeType.ALL, este quiere decir que esta sera la padre, al momento de ingresar, modificar o eliminar
	// la tabla optionAnswer sera afectada con la informacion que tenga el id de la pregunta que sea modificada

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
	private List<answer> optionsAnswerList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private category category;

/*	@JsonIgnore // para ignorar este campo al momento de mostrar los datos
	@OneToMany(mappedBy = "questionId") // name foregin key
	private List<question_answer> question_answers;*/

	public question(){ }

	public question(String question) {
		this.question = question;
	}

	public question(Integer idQuestion, String question) {
		this.idQuestion = idQuestion;
		this.question = question;
	}

	public question(Integer idQuestion, String question, category category) {
		this.idQuestion = idQuestion;
		this.question = question;
		this.category = category;
	}

	public Integer getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public com.example.demo.models.category getCategory() {
		return category;
	}

	public void setCategory(com.example.demo.models.category category) {
		this.category = category;
	}

	public List<answer> getOptionsAnswerList() {
		return optionsAnswerList;
	}

	public void setOptionsAnswerList(List<answer> optionsAnswerList) {
		this.optionsAnswerList = optionsAnswerList;
	}
}

/*
* En la entity pregunta sera la padre, la cual llevara la lista de respuesta
* y en la entity respuesta llevara el id de la pregunta que pertenezca cada respuesta.
* */

// https://www.youtube.com/watch?v=8NOMAxH2eFs
// https://www.youtube.com/watch?v=Dq0HmGxxGmI

/*
https://www.youtube.com/watch?v=KNHpwG-n4dI
https://www.youtube.com/watch?v=QpwqQCkhnVM

https://refactorizando.com/ejemplo-relacion-onetomany-hibernate/
https://danielme.com/2021/10/22/curso-jakarta-ee-jpa-con-hibernate-relaciones-de-herencia/
https://es.stackoverflow.com/questions/425757/spring-boot-jpa-herencia
https://www.adictosaltrabajo.com/2007/06/27/hib-inheritance/

*/