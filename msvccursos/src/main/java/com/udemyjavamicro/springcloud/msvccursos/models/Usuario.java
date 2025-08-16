package com.udemyjavamicro.springcloud.msvccursos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//no es un entity, es una conexion con el otro microservicio
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private Long id;

    private String nombre;

    private String email;

    private String password;
}
