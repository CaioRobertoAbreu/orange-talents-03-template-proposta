package br.com.zupacademy.caio.proposta.handler;

public class GlobalError {

    private String field;
    private String message;

    public GlobalError(String field, String message) {
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
