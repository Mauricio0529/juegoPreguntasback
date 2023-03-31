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
  Añade la configuracion necesaria al contexto(para el usuario) de spring
  para autorizar la peticion.
*/

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private userDetailsServiceImpl userDetailsService;

    @Autowired
    private jwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        /* obtener el token de la peticion, este se obtiene del header del navegador */
        String requestTokenHeader = request.getHeader("Authorization");

        String username = null;

        /* almacena el token */
        String jwtToken = null;

        System.out.println("Token request: " + requestTokenHeader);

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

            /* Se obtiene el token y quitamos el (Bearer ) */
            jwtToken = requestTokenHeader.substring(7);

            try {
                /* se extrae el username con base al token */
                username = this.jwtUtils.extractUsername(jwtToken);
            } catch (ExpiredJwtException expiredJwtException) {
                System.out.println("El token ha expirado");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            /* cuando el token no es valido (viene nulo y no es un token que empieza con bearer) */
            System.out.println("Token invalido , no empieza con bearer string");
        }

        System.out.println("username: " + username + "\n");

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            /* obtenemos el usuario */
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if(this.jwtUtils.validateToken(jwtToken, userDetails)) {

                /* getAuthorities son los roles, se obtiene el rol que tiene el usuario (userDetails) */
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                /* establecemos detalles */
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                /* establecemos el contexto del Security, se pasa la autentificacion(USUARIO Y ROL) */
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }  else {
            System.out.println("El token no es valido");
        }

        // doFilter ejecutamos el filtro y le pasamos la peticione request y la respuesta response
        filterChain.doFilter(request, response);
    }
}