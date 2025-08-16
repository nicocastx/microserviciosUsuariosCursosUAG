package com.udemyjavamicro.springcloud.usuarios.msvcusuarios.config.validations;

import com.udemyjavamicro.springcloud.usuarios.msvcusuarios.config.properties.EmailAvailableConfig;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailValidationConstraintAvailable implements ConstraintValidator<EmailValidation, String> {

    private EmailAvailableConfig emailAvailable;

    @Override
    public void initialize(EmailValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return emailAvailable.isCreacionEmails();
    }
}
