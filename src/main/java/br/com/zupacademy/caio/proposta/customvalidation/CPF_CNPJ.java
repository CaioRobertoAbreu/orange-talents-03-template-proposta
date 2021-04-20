package br.com.zupacademy.caio.proposta.customvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CPF_CNPJValidator.class)
public @interface CPF_CNPJ {

    String message() default "CPF/CNPJ inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
