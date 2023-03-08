package com.example.demo.service;

import com.example.demo.models.rolUser;
import com.example.demo.models.user;

import java.util.List;
import java.util.Set;

public interface userService {

    // guardamos el usuario y le pasamos un conjunto de roles donde nosotros la vamos a asignar
    public user guardarUsuario(user user, Set<rolUser> rolUser) throws Exception;

    /*
    NO SE PASA EL OBJETO DE USUARIO,
    YA QUE VAMOS A USAR EL METODO DEL REPOSITORY DE BUSCAR POR USERNAME
    */
    public user obtenerUsuarioByusername(String username);

    public void eliminarUsuarioById(Integer userId);

    public List<user> obtenerUsuarios();
}
