package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "optionsAnswer")
public class answer {
    /* la relacion apunta hacia la clase questionAndAnswer */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnswer;

    @Column(name = "name_option_answer")
    private String optionAnswer;

    @Column(name = "correct_answer")
    private Boolean correctAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionId")
    private question question;

    public answer(Integer idAnswer, String optionAnswer, boolean correctAnswer) {
        this.idAnswer = idAnswer;
        this.optionAnswer = optionAnswer;
        this.correctAnswer = correctAnswer;
    }

    public answer(String optionAnswer, boolean correctAnswer) {
        this.optionAnswer = optionAnswer;
        this.correctAnswer = correctAnswer;
    }
}