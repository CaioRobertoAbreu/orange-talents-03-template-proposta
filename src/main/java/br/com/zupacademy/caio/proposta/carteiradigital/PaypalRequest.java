package br.com.zupacademy.caio.proposta.carteiradigital;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PaypalRequest {

    @NotBlank
    @Email
    private String email;

    @JsonCreator
    public PaypalRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Carteira toCarteira(String id, Cartao cartao){

        return new Carteira(id, this.email, cartao);
    }
}
