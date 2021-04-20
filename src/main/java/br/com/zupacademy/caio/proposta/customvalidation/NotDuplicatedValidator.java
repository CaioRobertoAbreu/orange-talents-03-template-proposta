package br.com.zupacademy.caio.proposta.customvalidation;

import br.com.zupacademy.caio.proposta.exception.APIException;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotDuplicatedValidator implements ConstraintValidator<NotDuplicated, Object> {

    private Class<?> domain;
    private String field;
    @PersistenceContext
    private final EntityManager entityManager;

    public NotDuplicatedValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(NotDuplicated param) {
        domain = param.domain();
        field = param.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        List<?> resultList = entityManager.createQuery("SELECT 1 FROM " + domain.getSimpleName() + " WHERE " +
                field + " = " + value)
                .getResultList();

        Assert.isTrue(resultList.size() <= 1, "Há mais de um " + field + " campo com o mesmo valor " + value);

        if(!resultList.isEmpty()){
            throw new APIException(field, "Já existe uma proposta cadastrada para esse documento");
        }

        return true;
    }
}
