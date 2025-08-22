package com.udemyjavamicro.springcloud.usuarios.msvcusuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcusuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcusuariosApplication.class, args);
	}

}
