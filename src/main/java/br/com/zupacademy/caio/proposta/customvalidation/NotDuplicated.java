package br.com.zupacademy.caio.proposta.customvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = NotDuplicatedValidator.class)
public @interface NotDuplicated {

    String message() default "Já existe uma proposta cadastrada para esse documento";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> domain();

    String field();

}
