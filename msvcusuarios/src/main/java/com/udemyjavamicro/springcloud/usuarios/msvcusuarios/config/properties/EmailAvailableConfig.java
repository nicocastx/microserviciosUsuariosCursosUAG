package com.udemyjavamicro.springcloud.usuarios.msvcusuarios.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//simplemente queria repasar el paso de config por parametros
@Configuration
public class EmailAvailableConfig {
    @Value("${com.udemyjavamicro.springcloud.creacionEmails}")
    private boolean creacionEmails;

    public boolean isCreacionEmails() {
        return creacionEmails;
    }
}
