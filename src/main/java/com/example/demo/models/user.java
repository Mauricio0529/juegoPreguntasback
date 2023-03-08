package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class user implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "score")
    private Integer score = 0;

    @Column(name = "status_user")
    private Boolean statusUser = true;

    // un usuario podra tener muchos roles
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<rolUser> rolUser = new HashSet<>();

    public user(String email, String username, String password,
                Integer score, Boolean statusUser, Set<rolUser> rolUser) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.score = score;
        this.statusUser = statusUser;
        this.rolUser = rolUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<authority> authorities = new HashSet<>();

        this.rolUser.forEach((rol) -> {
            // obtenemos y agregamos el rol en el Set authorities
            authorities.add(new authority(rol.getRol().getNameRol()));
        });
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Indica si el usuario está bloqueado o desbloqueado. Un usuario bloqueado no puede ser autenticado.
    @Override
    public boolean isAccountNonLocked() {
        return true; // true es que no va hacer bloqueada
    }

    // Indica si las credenciales del usuario ( contraseña ) han expirado. Caducado las credenciales impiden la autenticación
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // para que la credencial no expire
    }

    // Indica si el usuario está habilitado o deshabilitado. Un usuario deshabilitado no puede ser autenticado.
    @Override
    public boolean isEnabled() {
        return statusUser;
    }
}