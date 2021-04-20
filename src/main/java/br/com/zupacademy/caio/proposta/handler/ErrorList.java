package br.com.zupacademy.caio.proposta.handler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ErrorList {

    private int status;
    private Instant instant = Instant.now();
    private List<GlobalError> globalErrors = new ArrayList<>();
    private List<FieldError> fieldErrors = new ArrayList<>();

    public ErrorList(int status) {
        this.status = status;
    }

    public void addGlobalError(GlobalError error){
        this.globalErrors.add(error);
    }

    public void addFieldError(FieldError error){
        this.fieldErrors.add(error);
    }

    public int getStatus() {
        return status;
    }

    public Instant getInstant() {
        return instant;
    }

    public List<GlobalError> getGlobalErrors() {
        return globalErrors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
