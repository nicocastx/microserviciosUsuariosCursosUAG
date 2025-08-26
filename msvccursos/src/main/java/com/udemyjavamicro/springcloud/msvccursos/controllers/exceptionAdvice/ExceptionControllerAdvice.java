package com.udemyjavamicro.springcloud.msvccursos.controllers.exceptionAdvice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionControllerAdvice  {

    private final HashMap<String, Object> error;

    public ExceptionControllerAdvice(HashMap<String, Object> error) {
        this.error = error;
    }

    //Error en una busqueda
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> noElement(NoSuchElementException e, HttpServletRequest request) {
        int statusCode = HttpStatus.NOT_FOUND.value();
        HashMap<String, Object> error = formatError(e, request, statusCode);
        error.put("statusCode:", String.valueOf(statusCode));
        return ResponseEntity.status(statusCode).body(error);
    }

    //Endpoint y/o metodo invalido
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> methodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        int statusCode = HttpStatus.METHOD_NOT_ALLOWED.value();
        HashMap<String, Object> error = formatError(e, request, statusCode);
        error.put("statusCode:", String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()));
        return ResponseEntity.status(statusCode).body(error);
    }

    //Spring Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        int statusCode = HttpStatus.BAD_REQUEST.value();
        List<String> validations = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(invalid -> invalid.getField() + " : " + invalid.getDefaultMessage())
                .toList();
        HashMap<String, Object> error = formatError(e, request, statusCode);
        error.put("validations:", validations);
        
        return ResponseEntity.status(statusCode).body(error);
    }


    //Error general
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception e, HttpServletRequest request) {
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        HashMap<String, Object> error = formatError(e, request, statusCode);
        return ResponseEntity.status(statusCode).body(error);
    }

    //formato mensaje de error
    private HashMap<String, Object> formatError(Exception e, HttpServletRequest request, int statusCode){
        HashMap<String, Object> error = new HashMap<>();
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
