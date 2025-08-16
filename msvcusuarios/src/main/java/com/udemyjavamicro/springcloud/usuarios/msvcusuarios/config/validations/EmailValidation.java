package com.udemyjavamicro.springcloud.usuarios.msvcusuarios.config.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

//Quise probar si podia implementar un component de spring en un validador... y resulto mas complejo de lo que esperaba
// CONCLUSION:
/**
 * Aun tengo el toque, resulta que si aparece un error de init, no es porque este mal el constructor, es porque por dentro esta pasando algo mal
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmailValidationConstraint.class})
public @interface EmailValidation {
    String message() default "El Email ya existe";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
