package com.udemyjavamicro.springcloud.msvccursos.clients;

import com.udemyjavamicro.springcloud.msvccursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvcusuarios", url = "http://localhost:8081")
//Las peticiones se setean con los metodos del controller
public interface UsuarioFeignClients {
    @GetMapping("/{id}")
    Usuario buscar(@PathVariable Long id);

    @PostMapping
    Usuario crear(@RequestBody Usuario usuario);

}
