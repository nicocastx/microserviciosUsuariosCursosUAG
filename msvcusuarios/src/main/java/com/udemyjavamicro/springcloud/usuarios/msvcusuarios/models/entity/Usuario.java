package com.udemyjavamicro.springcloud.usuarios.msvcusuarios.models.entity;

import com.udemyjavamicro.springcloud.usuarios.msvcusuarios.config.validations.EmailValidation;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    //Esto de la validacion iria mejor dentro de un DTO antes que modificar la clase que viene de la base, pero bueno, simplicidad y objetividad
    public interface NewUsuarioValidation {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idusuarios")
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotBlank(message = "El campo no puede ser vacio")
    private String nombre;

    @Column(name = "email")
    @Email
    @EmailValidation(groups = NewUsuarioValidation.class)
    private String email;

    @Column(nullable = false)
    @NotBlank
    private String password;

}
