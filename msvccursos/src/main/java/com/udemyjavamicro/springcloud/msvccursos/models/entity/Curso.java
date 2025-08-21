package com.udemyjavamicro.springcloud.msvccursos.models.entity;

import com.udemyjavamicro.springcloud.msvccursos.models.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "cursos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 3)
    @NotBlank
    private String nombre;
    //cascade me dice que cuando se cree o elimine un curso, tambien va a revisarse a los usuarios
    //orphanRemoval es para eliminar aquellas relaciones CursoUsuario que no tengan un curso
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private List<CursoUsuario> cursoUsuario;

    @Transient
    private List<Usuario> usuarios;

    public void addCursoUsuario(CursoUsuario cursoUsuario) {
        this.cursoUsuario.add(cursoUsuario);
    }

    public void removeCursoUsuario(CursoUsuario cursoUsuario) {
        this.cursoUsuario.remove(cursoUsuario);
    }

}


