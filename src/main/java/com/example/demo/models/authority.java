package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/*
 * ESTA CLASE IMPLEMENTA EL GrantedAuthority donde se implementa el metodo get
 * para obtener el authority.
 * dar autoridades o permisos a usuarios
 *
 * si es Administrador o es usuario regular
 * */

@AllArgsConstructor
public class authority implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}