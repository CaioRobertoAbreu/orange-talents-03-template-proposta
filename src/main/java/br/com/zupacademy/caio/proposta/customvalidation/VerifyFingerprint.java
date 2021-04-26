package br.com.zupacademy.caio.proposta.customvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = VerifyFingerprintValidator.class)
public @interface VerifyFingerprint {

    String message() default "Formato de biometria inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
