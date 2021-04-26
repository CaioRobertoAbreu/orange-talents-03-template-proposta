package br.com.zupacademy.caio.proposta.customvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;
import java.util.Base64.Decoder;

public class VerifyFingerprintValidator implements ConstraintValidator<VerifyFingerprint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        Decoder decoder = Base64.getDecoder();

        try {
            decoder.decode(value.getBytes());
            return true;
        } catch (IllegalArgumentException e){

            return false;
        }

    }
}
