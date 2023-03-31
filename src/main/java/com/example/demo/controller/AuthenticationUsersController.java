package com.example.demo.controller;

import com.example.demo.models.JwtRequest;
import com.example.demo.models.JwtResponse;
import com.example.demo.models.user;
import com.example.demo.securityWeb.jwtUtils;
import com.example.demo.service.userDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationUsersController {

    // AuthenticationManager del securityConfig
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private userDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private jwtUtils jwtUtils;


    /* obtenemos los datos del login */
    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            autenticarUsuario(jwtRequest.getUsername(),jwtRequest.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Usuario no encontrado, " + e.getMessage());
        }

        /* obtenemos el usuario */
        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());

        /* generamos y obtenemos el token */
        String token = this.jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticarUsuario(String username,String password) throws Exception {
        try {
            /* Autenticar con la clase UsernamePasswordAuthenticationToken si el
             * username y el password son correctos dara los permisos y va a autenticar el usuario
             */
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (DisabledException exception) { // usuario no encontrado
            throw new Exception("USUARIO DESHABILITADO, " + exception.getMessage());
        } catch (BadCredentialsException badCredentialsException) { // credenciales incorrectas
            throw new Exception("Credenciales invalidas, " + badCredentialsException.getMessage());
        }
    }

    /* obtener el usuario actual */
    @GetMapping("/actualUser")
    public user getUserCurrent(Principal principal) {
        return (user) this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
    }
}