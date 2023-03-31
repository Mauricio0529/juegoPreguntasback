package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "rol_user")
public class rolUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private user user;

    @ManyToOne
    private rol rol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.example.demo.models.user getUser() {
        return user;
    }

    public void setUser(com.example.demo.models.user user) {
        this.user = user;
    }

    public com.example.demo.models.rol getRol() {
        return rol;
    }

    public void setRol(com.example.demo.models.rol rol) {
        this.rol = rol;
    }
}