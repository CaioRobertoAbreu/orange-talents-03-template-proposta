package br.com.zupacademy.caio.proposta.handler;

public class FieldError {

    private String field;
    private String message;

    public FieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
