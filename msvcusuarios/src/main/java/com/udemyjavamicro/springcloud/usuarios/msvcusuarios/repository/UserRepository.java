package com.udemyjavamicro.springcloud.usuarios.msvcusuarios.repository;

import com.udemyjavamicro.springcloud.usuarios.msvcusuarios.models.entity.Usuario;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
public interface UserRepository extends CrudRepository<Usuario, Long> {
    boolean existsByEmail(String email);
}
