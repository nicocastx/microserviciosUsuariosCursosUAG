package com.udemyjavamicro.springcloud.usuarios.msvcusuarios.models.errors;

import java.util.HashMap;

public class ExceptionResponse {
    private Exception exception;
    private int httpCode;

    public ExceptionResponse(Exception exception, int httpCode) {
        this.exception = exception;
        this.httpCode = httpCode;
    }

    public HashMap<String, String> showError(){
        HashMap<String, String> error = new HashMap<>();
        error.put("Mensaje", exception.getMessage());
        error.put("Tipo de error", exception.toString());
        error.put("Codigo de error", String.valueOf(httpCode));
        return error;
    }
}
