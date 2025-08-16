package com.udemyjavamicro.springcloud.msvccursos.repositories;

import com.udemyjavamicro.springcloud.msvccursos.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {

}
