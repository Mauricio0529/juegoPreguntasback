package com.example.demo.service;

import com.example.demo.models.rolUser;
import com.example.demo.models.user;
import com.example.demo.repository.rolRepository;
import com.example.demo.repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class userServiceImpl  implements userService {

    private usersRepository usuarioRepository;
    private rolRepository rolRepository;

    @Autowired
    public userServiceImpl(usersRepository usuarioRepository, rolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public user guardarUsuario(user user, Set<rolUser> rolUsuarios) throws Exception {
        /* pasamos el username para buscar un usuario */
        // usuario username = usuarioRepository.findByUsername(usuarios.getUsername());
        Optional<user> username = Optional.ofNullable(usuarioRepository.findByUsername(user.getUsername()));
        if(username.isPresent()){ // usuario != null
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
            //return usuario.get();
        }

        /* Obtenemos y guardamos el rol
        recorremos el objecto y lo guardamos en la bd
         */
        for(rolUser rolUsuario: rolUsuarios){
            // obtenemos el objeto de la clase rolUsuario
            // guardamos cada rol que hay en el objecto
            rolRepository.save(rolUsuario.getRol());
        }

        // se pasa la lista de RolUsuarios y agregamos el rol en la lista de la clase usuario
        user.getRolUser().addAll(rolUsuarios);

        // se guarda los datos de usuario y el rol
        return usuarioRepository.save(user);
    }

    @Override
    public user obtenerUsuarioByusername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void eliminarUsuarioById(Integer userId) {
        usuarioRepository.deleteById(userId);
    }

    @Override
    public List<user> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }
}