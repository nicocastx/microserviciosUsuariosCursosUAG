package com.udemyjavamicro.springcloud.msvccursos.services;

import com.udemyjavamicro.springcloud.msvccursos.models.Usuario;
import com.udemyjavamicro.springcloud.msvccursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar ();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);

    // --- TODOS LOS METODOS DEVUELVEN OPTIONAL PORQUE DEPENDEN DE CONECTARSE A OTRO SERVICIO ---
    //Esto seria para asignar un usuario a un curso
    Optional<Usuario> asignarUsuario(Usuario usuario, Long idCurso);
    //Esto seria para crear un nuevo usuario, y asignarlo a un curso luego de ser creado
    //desde el msvc de cursos
    Optional<Usuario> crearUsuario(Usuario usuario, Long idCurso);
    //Esto seria para desasignar un Usuario de un curso
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long idCurso);
    Optional<Curso> porIdConUsuarios(Long idCurso);
}
