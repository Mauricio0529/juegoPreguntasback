package com.example.demo.service;

import com.example.demo.dto.responseDto.userScoreDto;
import com.example.demo.models.rolUser;
import com.example.demo.models.user;

import java.util.List;
import java.util.Set;

public interface userService {

    /* guardamos el usuario y le pasamos un conjunto de roles donde nosotros lo vamos asignar */
    public user saveUser(user user, Set<rolUser> rolUser) throws Exception;

    public user getUserByusername(String username);

    public void deleteUserById(Integer userId);

    public List<user> getAllUsers(); // modificar este, PARA que sea DTO

    public user getUserCurrent();

    public user addScoreUser(Integer score);

    public Integer getScoreUserOfCurrent();

    public List<userScoreDto> getUsersByScoreHigh();
}