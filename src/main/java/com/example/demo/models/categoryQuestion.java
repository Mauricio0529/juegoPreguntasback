package com.example.demo.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/*
@Entity(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
*/
public class categoryQuestion {
/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nameCategory;
    private Boolean statusCaretory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
    private List<question> questionList;

    public categoryQuestion (Integer id, String nameCategory, Boolean statusCaretory) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.statusCaretory = statusCaretory;
    }
*/
}