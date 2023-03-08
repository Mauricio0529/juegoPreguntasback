package com.example.demo.securityWeb;

import com.example.demo.service.userDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
ESTE ES UN FILTRO
SE ENCARGA DE VALIDAR DEL USUARIO Y DE LA PETICION REQUEST

PARA LAS CONFIGURACIONES DEL JWT SE COLOCA @Component o en ocaciones @Service

* Comprobar la existencia del token, valida si el token esta correcto.
* Si el token sale bien, este añade la configuracion necesaria al contexto(para el usuario) de spring
  para autorizar la peticion setUpSrpingAuthentication().
* */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private userDetailsServiceImpl userDetailsService;

    @Autowired
    private jwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // contiene el hash para encriptar. obtener el token de la peticion
        // creo que el getHeader("Authorization"), obtiene el token almacenado en el header del navegador cuando ya inicie sesion
        String requestTokenHeader = request.getHeader("Authorization"); // obtiene el header del navegador
        String username = null;
        // este va a tener el token
        String jwtToken = null;

        /*
        * si la peticion no viene vacia y que realize la peticion token entra el if.
        * se condiciona el "Bearer ", ya que al realizar la peticion para el token,
          este token viene con el barer.
          * en pocas palabras el bearer es el estandar de un token cuando se hace la peticion request
         * */

        System.out.println("Token request " + requestTokenHeader);

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            /* Se obtiene el token y se quita la cabecera y el espacio del (Bearer )
               para tener solo el token sin el bearer
            */
            jwtToken = requestTokenHeader.substring(7);

            try {
                /*se extrae el username con base al token*/
                username = this.jwtUtils.extractUsername(jwtToken);
            } catch (ExpiredJwtException expiredJwtException) {
                System.out.println("El token ha expirado");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // cuando el token no es valido ( viene nulo y no es un token que empieza con bearer)
            System.out.println("Token invalido , no empieza con bearer string");
        }


        /* VALIDAMOS LA PETICION REQUEST
            Cuando el username traiga el texto(no este vacio)
            y
            si en el contexto de la seguridad de la autentifiacion no es valida entra al if
        */

        // REPETIR LA PARTE DONDE IMPLEMENTA SPRING SECURITY
        System.out.println("El username " + username);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // se pasa y carga el username,
            // el UserDetails es del JWT ya que el metodo de loadUser retorna UserDetails
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            /* Si el token no es valido (que el usuario y el token no sean validos) no entra a este if.
            *  Se usa el condicional del metodo validateToken de jwtUtils
            *
            se pasa el token y el usuario encontrado al validateToken que es del contrato
            */

            if(this.jwtUtils.validateToken(jwtToken,userDetails)) {
                // si el token es valido, establecemos estos detalles
                // las getAuthorities son los roles, se obtiene los roles o el rol
                /* le pasamos el username y obtenemos el rol o los roles que tiene el usuario */
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // establecemos detalles.
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // lo establecemos en el contexto del Security, se pasa la autentificacion(NOMBRE DE USUARIO Y EL ROL)
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }  else {
            System.out.println("El token no es valido");
        }
        // doFilter ejecutamos el filtro y le pasamos la peticione request y la respuesta response
        filterChain.doFilter(request, response);
    }
}