package com.udemyjavamicro.springcloud.usuarios.msvcusuarios.services;

import com.udemyjavamicro.springcloud.usuarios.msvcusuarios.models.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<Usuario> list();
    Optional<Usuario> porId(Long id);
    Optional<Usuario> guardar(Usuario usuario);
    void eliminar(Long id);
    Optional<List<Usuario>> buscarVariosIds(List<Long> ids);
}
