package com.udemyjavamicro.springcloud.msvccursos.controllers.exceptionAdvice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice  {

    private final View error;

    public ExceptionControllerAdvice(View error) {
        this.error = error;
    }

    //no se encontro el elemento
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> noElement(NoSuchElementException e, HttpServletRequest request) {
        int statusCode = HttpStatus.NOT_FOUND.value();
        HashMap<String, String> error = formatError(e, request, statusCode);
        error.put("statusCode:", String.valueOf(statusCode));
        return ResponseEntity.status(statusCode).body(error);
    }

    //metodo no soportado por la aplicacion
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> methodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        int statusCode = HttpStatus.METHOD_NOT_ALLOWED.value();
        HashMap<String, String> error = formatError(e, request, statusCode);
        error.put("statusCode:", String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()));
        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception e, HttpServletRequest request) {
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        HashMap<String, String> error = formatError(e, request, statusCode);
        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        int statusCode = HttpStatus.BAD_REQUEST.value();
        String validations = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(invalid -> invalid.getField() + " : " + invalid.getDefaultMessage())
                .collect(Collectors.joining("\n"));
        HashMap<String, String> error = formatError(e, request, statusCode);
        error.put("validations:", validations);

        return ResponseEntity.status(statusCode).body(error);
    }

    //errores generales capturados
    private HashMap<String, String> formatError(Exception e, HttpServletRequest request, int statusCode){
        HashMap<String, String> error = new HashMap<>();
        StackTraceElement description = e.getStackTrace()[0];
        error.put("statusCode:", String.valueOf(statusCode));
        error.put("message", e.getMessage().isBlank() ? "Error General" : e.getMessage());
        error.put("class",  description.getClassName());
        error.put("function", description.getMethodName());
        error.put("method:", request.getMethod());
        error.put("path:", request.getRequestURI());
        return error;
    }


}
