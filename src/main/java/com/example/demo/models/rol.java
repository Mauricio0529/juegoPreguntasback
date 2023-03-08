package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_rol")
    private String nameRol; // si es Administrador o usuario regular

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    private Set<rolUser> rolUser = new HashSet<>();

    public rol(String nameRol, Set<rolUser> rolUser) {
        this.nameRol = nameRol;
        this.rolUser = rolUser;
    }
}