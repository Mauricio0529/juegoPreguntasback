package com.example.demo.controller;


import com.example.demo.models.rol;
import com.example.demo.models.rolUser;
import com.example.demo.models.user;
import com.example.demo.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*") // el cors
public class userController {
    @Autowired
    private userService usuarioService;

    @PostMapping("/")
    public user guardarUsuario(@RequestBody user user) throws Exception{
        /* el @RequestBody manda el objeto del usuario con sus datos */

    /*
        NO SE AGREGA LOS DATOS DEL USUARIO MANUALMENTE EN ESTE EDPOINT,
        SI NO, EN EL FRONT O EN EL POSTMAN SE PASAN O AGREGAN LOS DATOS AL USUARIO
        Y LOS DATOS QUE SE AGREGAN MANUALMENTE EN ESTE METODO, ES PARA QUE SEA POR DEFECTO
    */
        Set<rolUser> roles = new HashSet<>();

        rol rol = new rol();
        //rol.setId(1);
        rol.setNameRol("INVITADO");
        /*
        bd_login
        INVITADO
        ADMIN
         */

        /* se agrega el rol y el usuario a la tabla principal*/
        rolUser rolUser = new rolUser();
        rolUser.setUser(user);
        rolUser.setRol(rol);

        roles.add(rolUser);
        return usuarioService.guardarUsuario(user,roles);
    }

    @GetMapping("/{username}")
    public user obtenerUsuarioByUsername(@PathVariable("username") String username){
        return usuarioService.obtenerUsuarioByusername(username);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuarioById(@PathVariable("id") Integer id){
        usuarioService.eliminarUsuarioById(id);
    }

    @GetMapping("/listar")
    public List<user> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }
}
