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

    /*@JsonIgnore // para ignorar este campo al momento de mostrar los datos
    @OneToMany(mappedBy = "optionsAnswerId") // name foregin key
    private List<question_answer> question_answers;*/

}

/*
* https://es.stackoverflow.com/questions/467792/como-relacionar-correctamente-entidades-en-spring-boot
* https://www.javaguides.net/2019/04/jackson-jsonignore-jsonignoreproperties-and-jsonignoretype.html#:~:text=%40JsonIgnore%20is%20used%20to%20ignore,annotated%20at%20the%20class%20level.
* https://www.arquitecturajava.com/jpa-onetomany/
* https://howtodoinjava.com/jaxb/xmlrootelement-annotation/
* https://www.tutorialspoint.com/es/jpa/jpa_entity_relationships.htm
* */