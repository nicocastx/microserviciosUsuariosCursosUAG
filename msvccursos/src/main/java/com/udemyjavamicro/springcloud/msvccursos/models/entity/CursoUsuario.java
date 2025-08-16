package com.udemyjavamicro.springcloud.msvccursos.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="cursos_usuarios")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class CursoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="usuario_id")
    private Long usuarioId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof CursoUsuario o)){
            return false;
        }
        return this.usuarioId != null && this.usuarioId.equals(o.usuarioId);
    }
}
