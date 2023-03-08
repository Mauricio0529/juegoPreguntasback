package com.example.demo.securityWeb;

import com.example.demo.service.userDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration // ya que una clase de confi va a tener anotaciones @Beans
@EnableGlobalMethodSecurity(prePostEnabled = true) // prePostEnabled = true -> accedemos a los metodos @PreAuthorize y @PostAuthorize.
public class configurationAPI extends WebSecurityConfigurerAdapter { // version: 2.6.6

    @Autowired
    private JwtAuthenticationEntryPoint unaauthorizeHandler;

    @Autowired
    private userDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Override
    @Bean // para poder inyectar o usar este metodo
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // para encriptar la contraseña
    @Bean
    public PasswordEncoder passwordEncoder() {
        // se encripta la contraseña
        return NoOpPasswordEncoder.getInstance();
    }

    // borrar este para ver el error que sale en el postman.
    // se le indica que al usuario se pasa una encriptacion a la contraseña
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // AuthenticationManagerBuilder, permite contruir facilmente una autentificacion en memoria
        // esta utiliza el userDetailsService
        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    /* para conceder permisos de la web o en nuestra pagina web */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf() // para prevenir ataques de solicitudes HTTP, es como un token
                .disable() // aqui estamos desactivando el csrf, toca borrarlo, ya que este biene activado por defecto
                .cors()
                .disable()
                .authorizeRequests()

                /*
                 * a estas dos rutas se le permite tod-o no se porque.
                 * esta ruta /generate-token nos va a generar un token para logearse.
                 * ya que al generar el token, podremos acceder a cualquier ruta si el
                 * token no tiene errores, por eso no se colocó mas rutas
                 */
                .antMatchers("/generate-token", "/usuarios/").permitAll()

                /* si yo ejecuto un metodo OPTIONS se permite t-o-d-o
                 * Es utilizado para describir las opciones de comunicacion para el front,
                 * este se utiliza mucho con CORS para validar si el servidor acepta peticiones de
                 * diferentes origenes, si no es un get, put, post, delete, update, este validara si
                 * se acepta la peticion distinta a las anteriores.
                 * En conclucion este permite to-do tipo de peticiones Http.
                 * */
                .antMatchers(HttpMethod.OPTIONS).permitAll()

                /* cualquier otra peticion se autentica, no todos los usuarios podra realizar
                 * peticiones distintas si no estan autorizados o tienen el permiso de ingresar
                 * una peticion
                 * */
                .anyRequest().authenticated()
                .and()

                /*
                Si hay un usuario no esta autorizado me arroje la excepcion que tiene
                esa clase JwtAuthenticationEntryPoint
                */
                .exceptionHandling().authenticationEntryPoint(unaauthorizeHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}