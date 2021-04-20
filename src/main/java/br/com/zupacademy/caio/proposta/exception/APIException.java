package br.com.zupacademy.caio.proposta.exception;

public class APIException extends RuntimeException{

    private String field;
    private String message;

    public APIException(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
