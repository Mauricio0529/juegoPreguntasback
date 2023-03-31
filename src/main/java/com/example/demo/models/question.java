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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
	private List<answer> optionsAnswerList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private category category;

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

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
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