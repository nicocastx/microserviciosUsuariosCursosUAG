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
    @PutMapping("/asignar-usuario/{cursoId}")
    ResponseEntity<?> asignarUsuario(@PathVariable Long cursoId, @RequestBody Usuario usuario);
}
