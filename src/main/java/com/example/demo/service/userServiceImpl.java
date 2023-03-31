package com.example.demo.service;

import com.example.demo.dto.mapperDto.mapperDto;
import com.example.demo.dto.responseDto.userScoreDto;
import com.example.demo.models.rolUser;
import com.example.demo.models.user;
import com.example.demo.repository.rolRepository;
import com.example.demo.repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class userServiceImpl  implements userService {

    private usersRepository userRepository;
    private rolRepository rolRepository;

    public userServiceImpl(usersRepository userRepository, rolRepository rolRepository) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
    }

    @Autowired
    private userDetailsServiceImpl userDetailsServiceImpl;

    @Override
    public user saveUser(user user, Set<rolUser> rolUsuarios) throws Exception {

        Optional<user> username = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));

        if(username.isPresent()){
            throw new Exception("El usuario ya esta presente");
        }

        /* Obtenemos y guardamos el rol que hay en el userController */
        for(rolUser rolUsuario: rolUsuarios){
            rolRepository.save(rolUsuario.getRol());
        }

        /* se pasa la lista de rolUsuarios y agregamos el rol de la lista que hay en la clase user */
        user.getRolUser().addAll(rolUsuarios);

        return userRepository.save(user);
    }

    @Override
    public user getUserByusername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<user> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public user getUserCurrent() {
        return userDetailsServiceImpl.userCurrent();
    }

    /* se guarda el score cuando el nivel termine */
    @Override
    public user addScoreUser(Integer score) {
        user user = getUserCurrent();
        user.setScore(score);
        return userRepository.save(user);
    }

    @Override
    public Integer getScoreUserOfCurrent() {
        user user = getUserCurrent();
        return user.getScore();
    }

    @Override //
    public List<userScoreDto> getUsersByScoreHigh() {
        List<user> userAllList  = userRepository.findAll();
        List<user> users = new ArrayList<>();

        userAllList.stream()
                .forEach(x -> {
                    for (rolUser rol: x.getRolUser()) {
                        if(rol.getRol().getNameRol().equals("INVITADO")) users.add(x);
                    }
                });

        orderScoreUser(users);

        return mapperDto.userScoreDtoList(users);
    }

    private List<user> orderScoreUser(List<user> userList) {
        Collections.sort(userList, new Comparator<user>() {
            @Override
            public int compare(user user1, user user2) {
                return Integer.compare(user2.getScore(), user1.getScore());
            }
        });
        return userList;
    }
}