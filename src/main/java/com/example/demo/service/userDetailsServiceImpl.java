package com.example.demo.service;

import com.example.demo.models.user;
import com.example.demo.repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userDetailsServiceImpl implements UserDetailsService {

    private usersRepository userRepository;

    @Autowired
    public userDetailsServiceImpl(usersRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*LOCALIZAR O BUSCAR AL USUARIO POR SU USERNAME*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user usuarioEncontrado = this.userRepository.findByUsername(username);
        if(usuarioEncontrado == null) {
            throw new UsernameNotFoundException("Usuario no encontradoG");
        }
        return usuarioEncontrado;
    }
}