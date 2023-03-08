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

    // usamos el AuthenticationManager del securityConfig
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private userDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private jwtUtils jwtUtils;

    /* Vamos a generar el token, por ello se hace un POST.
     * autenticamos. enviamos el username y password y validamos si esta correcto para generar el token
     * ejemplo: es como inciciar sesion, ingresando el username y password para iniciar sesion,
     * si es correcto se genera el token e inicia sesion, y si la contraseña o el username son incorrectos
     * el token no se generara.
     * */

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            // pasamos las varibles del username y password para hacer la peticion jwtRequest
            // el jwtRequest va a obtener los datos por medio de la peticion del login.
            autenticarUsuario(jwtRequest.getUsername(),jwtRequest.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Usuario no encontrado, " + e.getMessage());
        }

      /*
        Creo que Se inica sesion
        cargamos el username de la peticion de jwtRequest con el loadUserByUsername de userDetailsServiceImpl
      */
        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());

        // le pasamos los datos del usuario para crear y generar el token con el metodo de la clase jwtUtils
        String token = this.jwtUtils.generateToken(userDetails);

        // enviamos la respuesta response, el token
        /* CON BASE A ESTE TOKEN DE ACCESO, PODREMOS IR A CUALQUIER RUTA
         * VALIDANDO SI ESTE SEA CORRECTO, SI ESTE LO ES,
         * PODRA ACCEDER A LA RUTA QUE SE SOLICITE PASANDO EL TOKEN SI ES INVITADO O ADMINSTRADOR.
         * */
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticarUsuario(String username,String password) throws Exception {
        try {
            /* Autenticando, validar o verificar con la clase UsernamePasswordAuthenticationToken si el
             * username y el password son correctos dara los permisos y va a autenticar el usuario*/
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (DisabledException exception) { // usuario no encontrado
            throw new Exception("USUARIO DESHABILITADO, " + exception.getMessage());
        } catch (BadCredentialsException badCredentialsException) { // credenciales incorrectas
            throw new Exception("Credenciales invalidas, " + badCredentialsException.getMessage());
        }
    }

    // obtener el usuario actual
    @GetMapping("/actualUser")
    public user getUserCurrent(Principal principal) {
        // se castea ya que se retorna un UserDeails principal.getName()
        return (user) this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
    }
}