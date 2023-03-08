package com.example.demo.securityWeb;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* ESTA CLASE SE ENCARGA DE MANEJAR LOS ERRORES O LAS EXCEPCIONES DE LA AUTENTIFICACION DEL USUARIO
un esquema de autentificacion
* PARA LAS CONFIGURACIONES DEL JWT SE COLOCA @Component o en ocaciones @Service
* */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    //  esquema de autenticación
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // para indicar que este usuario no esta autorizado
        // SC_UNAUTHORIZED: Código de estado ( 401 ) que indica que la solicitud requiere HTTP autenticación.
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario no autorizado");
    }
}
