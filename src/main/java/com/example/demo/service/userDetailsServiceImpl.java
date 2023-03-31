package com.example.demo.service;

import com.example.demo.models.user;
import com.example.demo.repository.usersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userDetailsServiceImpl implements UserDetailsService {

    private usersRepository userRepository;

    public userDetailsServiceImpl(usersRepository userRepository) {
        this.userRepository = userRepository;
    }

    private user usuarioEncontrado;

    /* LOCALIZAR O BUSCAR AL USUARIO POR SU USERNAME */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        usuarioEncontrado = this.userRepository.findByUsername(username);
        if(usuarioEncontrado == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return usuarioEncontrado;
    }

    public user userCurrent() {
        return usuarioEncontrado;
    }
}