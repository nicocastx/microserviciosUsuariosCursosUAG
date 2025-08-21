package com.udemyjavamicro.springcloud.msvccursos.controllers;

import com.udemyjavamicro.springcloud.msvccursos.models.Usuario;
import com.udemyjavamicro.springcloud.msvccursos.models.entity.Curso;
import com.udemyjavamicro.springcloud.msvccursos.services.CursoService;
import feign.FeignException;
import feign.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class CursoControllerImpl implements CursoController {

    private CursoService cursoService;

    @Override
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(cursoService.listar());
    }

    @Override
    public ResponseEntity<?> porId(Long id) {
        Optional<Curso> c = cursoService.porIdConUsuarios(id);
        if (c.isPresent()) {
            return ResponseEntity.ok(cursoService.porId(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> guardar(Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = validar(result);
            return ResponseEntity.badRequest().body(errors);
        }
        Curso cursoDb = cursoService.guardar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDb);
    }

    @Override
    public ResponseEntity<?> editar(Long id, Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = validar(result);
            return ResponseEntity.badRequest().body(errors);
        }
        Curso cursoDb = cursoService.porId(id).orElseThrow();
        cursoDb.setNombre(curso.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(cursoDb));

    }

    @Override
    public ResponseEntity<?> eliminar(Long id) {
        cursoService.eliminar(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> asignarUsuario(Long cursoId, Usuario usuario) {
        Optional<Usuario> u = null;
        try {
            u = cursoService.asignarUsuario(usuario, cursoId);
            if (u.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(u.get());
            }
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/crear-usuario/{idCurso}")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario, @PathVariable Long idCurso) {
        Optional<Usuario> u = null;
        try {
            u = cursoService.crearUsuario(usuario, idCurso);
            if (u.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(u.get());
            }
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-usuario/{idCurso}")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Usuario usuario, @PathVariable Long idCurso) {
        Optional<Usuario> u = null;
        try {
            u = cursoService.eliminarUsuario(usuario, idCurso);
            if (u.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(u.get());
            }
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    private Map<String, String> validar(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err ->
                errors.put(err.getField(), "Campo " + err.getField() + " " + err.getDefaultMessage())
        );
        return errors;
    }

}
