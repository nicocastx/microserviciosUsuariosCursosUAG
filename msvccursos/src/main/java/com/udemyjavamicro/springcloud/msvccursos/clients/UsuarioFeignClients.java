package com.udemyjavamicro.springcloud.msvccursos.clients;

import com.udemyjavamicro.springcloud.msvccursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvcusuarios", url = "http://localhost:8001")
//Las peticiones se setean con los metodos del controller
public interface UsuarioFeignClients {
    @GetMapping("/{id}")
    Usuario buscar(@PathVariable Long id);

    @PostMapping
    Usuario crear(@RequestBody Usuario usuario);

    @GetMapping("/buscarPorIds")
    public List<Usuario> buscarPorIds(@RequestParam List<Long> ids);

}
