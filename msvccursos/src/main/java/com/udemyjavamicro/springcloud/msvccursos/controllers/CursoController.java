package com.udemyjavamicro.springcloud.msvccursos.controllers;

import com.udemyjavamicro.springcloud.msvccursos.models.Usuario;
import com.udemyjavamicro.springcloud.msvccursos.models.entity.Curso;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public interface CursoController {
    @GetMapping
    ResponseEntity<?> listar();
    @GetMapping("/{id}")
    ResponseEntity<?> porId(@PathVariable Long id);
    @PostMapping
    ResponseEntity<?> guardar(@Valid @RequestBody Curso curso, BindingResult result);
    @PutMapping("/{id}")
    ResponseEntity<?> editar(@PathVariable Long id, @Valid @RequestBody Curso curso, BindingResult result);
    @DeleteMapping("/{id}")
    ResponseEntity<?> eliminar(@PathVariable Long id);

    @PostMapping("/crear-usuario/{idCurso}")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario, @PathVariable Long idCurso);
    @PutMapping("/asignar-usuario/{idCurso}")
    ResponseEntity<?> asignarUsuario(@PathVariable Long idCurso , @RequestBody Usuario usuario);
    @DeleteMapping("/eliminar-usuario/{idCurso}")
    public ResponseEntity<?> eliminarCursoUsuarios(@RequestBody Usuario usuario, @PathVariable Long idCurso);
    @DeleteMapping("/eliminar-usuario-cursos/{id}")
    ResponseEntity<?> eliminarCursoUsuarios(@PathVariable Long id);
}
