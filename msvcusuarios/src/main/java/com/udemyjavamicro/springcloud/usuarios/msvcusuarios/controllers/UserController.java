package com.udemyjavamicro.springcloud.usuarios.msvcusuarios.controllers;

import com.udemyjavamicro.springcloud.usuarios.msvcusuarios.models.entity.Usuario;
import com.udemyjavamicro.springcloud.usuarios.msvcusuarios.models.errors.ExceptionResponse;
import com.udemyjavamicro.springcloud.usuarios.msvcusuarios.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
//@RequestMapping("/usuarios"), no lo agrego porque cada msvc ya va a tener su ruta
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //esto se hace por default creo
    @GetMapping("/")
    public List<Usuario> listar() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.porId(id).orElseThrow());
        } catch (NoSuchElementException e) {
            System.out.println("El usuario buscado no existe");
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println("Error en GET por ID de usuario desconocido");
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated(Usuario.NewUsuarioValidation.class) @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()){
            Map<String, String> errors = validar(result);
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.guardar(usuario).orElseThrow());
        } catch (Exception e) {
            System.out.println("Error en POST de Usuario");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ExceptionResponse(e, HttpStatus.INTERNAL_SERVER_ERROR.value()).showError());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Validated @RequestBody Usuario newUsuario, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()){
            Map<String, String> errors = validar(result);
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            Usuario usuario = userService.porId(id).orElseThrow();
            usuario.setNombre(newUsuario.getNombre());
            usuario.setPassword(newUsuario.getPassword());
            usuario.setEmail(newUsuario.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.guardar(usuario).orElseThrow());
        } catch (Exception e) {
            System.out.println("Error en PUT de Usuario");
            return ResponseEntity.internalServerError().body(new ExceptionResponse(e, HttpStatus.INTERNAL_SERVER_ERROR.value()).showError());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if (userService.porId(id).isPresent()){
                userService.eliminar(id);
                return ResponseEntity.ok(new HashMap<>().put("Exito", "El usuario de ID " + id + " se elimino correctamente."));
            } else {
                throw new NoSuchElementException("El usuario no existe");
            }
        } catch (NoSuchElementException e) {
            System.out.println("El usuario buscado no existe");
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println("Error en DELETE de Usuario");
            return ResponseEntity.internalServerError().body(new ExceptionResponse(e, HttpStatus.INTERNAL_SERVER_ERROR.value()).showError());
        }


    }

    private Map<String, String> validar(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err ->
                errors.put(err.getField(), "Campo " + err.getField() + " " + err.getDefaultMessage())
        );
        return errors;
    }
}
