package com.udemyjavamicro.springcloud.usuarios.msvcusuarios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvccursos", url = "${local.url}:8002")
public interface CursoFeignClient {

    @DeleteMapping("/eliminar-usuario-cursos/{id}")
    void eliminarCursoUsuarios(@PathVariable Long id);

}
