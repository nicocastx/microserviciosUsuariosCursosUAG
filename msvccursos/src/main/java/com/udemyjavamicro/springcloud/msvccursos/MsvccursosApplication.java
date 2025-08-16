package com.udemyjavamicro.springcloud.msvccursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.HashMap;

@SpringBootApplication
@EnableFeignClients
public class MsvccursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvccursosApplication.class, args);
	}

}
