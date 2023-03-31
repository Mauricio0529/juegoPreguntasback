package com.example.demo.controller;

import com.example.demo.dto.responseDto.userScoreDto;
import com.example.demo.models.rol;
import com.example.demo.models.rolUser;
import com.example.demo.models.user;
import com.example.demo.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class userController {

    @Autowired
    private userService usuarioService;

    @PostMapping("/")
    public user saveUser(@RequestBody user user) throws Exception{

        Set<rolUser> roles = new HashSet<>();

        rol rol = new rol();

        /* INVITADO, ADMIN */
        rol.setNameRol("INVITADO");

        /* se agrega el rol y usuario a la tabla principal */
        rolUser rolUser = new rolUser();
        rolUser.setUser(user);
        rolUser.setRol(rol);

        roles.add(rolUser);
        return usuarioService.saveUser(user,roles);
    }

    @GetMapping("/{username}")
    public user getUserByUsername(@PathVariable("username") String username){
        return usuarioService.getUserByusername(username);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Integer id){
        usuarioService.deleteUserById(id);
    }

    @GetMapping("/listar") // all y DTO
    public List<user> getAllUsers(){
        return usuarioService.getAllUsers();
    }

    /* actual usuario */
    @GetMapping("/actualUser")
    public ResponseEntity<user> getUserCurrent() {
        return ResponseEntity.ok(usuarioService.getUserCurrent());
    }

    /* listar usuario por puntaje mas alto */
    @GetMapping("/usersScore")
    public ResponseEntity<List<userScoreDto>> getUsersByScoreHigh() {
        return ResponseEntity.ok(usuarioService.getUsersByScoreHigh());
    }

    @GetMapping("/getScore")
    public ResponseEntity<Integer> getScoreUserCurrent() {
        return ResponseEntity.ok(usuarioService.getScoreUserOfCurrent());
    }
}