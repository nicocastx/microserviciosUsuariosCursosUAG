package com.udemyjavamicro.springcloud.msvccursos.repositories;

import com.udemyjavamicro.springcloud.msvccursos.models.entity.Curso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {

    //Query por si solo no puede modificar o eliminar o insertar (aunque mejor es save) un registro, por lo que
    //se usa modifying para que sean efectivos los cambios
    @Modifying
    @Query("delete from CursoUsuario cu where cu.usuarioId=?1")
    void eliminarCursoUsuarioPorId(Long id);
}

