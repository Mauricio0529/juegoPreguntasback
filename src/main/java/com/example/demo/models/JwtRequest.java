package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtRequest {
    /* ESTA ES PARA CUANDO VALLAMOS A INICIAR SESION */
    private String username;
    private String password;
}