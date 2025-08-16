package com.udemyjavamicro.springcloud.usuarios.msvcusuarios.config.validations;


import com.udemyjavamicro.springcloud.usuarios.msvcusuarios.models.entity.Usuario;
import com.udemyjavamicro.springcloud.usuarios.msvcusuarios.repository.UserRepository;
import com.udemyjavamicro.springcloud.usuarios.msvcusuarios.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailValidationConstraint implements ConstraintValidator<EmailValidation, String> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(EmailValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean existe = this.repository.existsByEmail(s);
        System.out.println(existe);
        return !existe;
    }
}
