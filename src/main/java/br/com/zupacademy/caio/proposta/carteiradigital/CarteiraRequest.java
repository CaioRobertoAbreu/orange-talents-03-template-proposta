package br.com.zupacademy.caio.proposta.carteiradigital;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Locale;

public class CarteiraRequest {

    @NotBlank
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    @NotBlank
    private String tipoCarteira;

    public CarteiraRequest(String email, String tipoCarteira) {
        this.email = email;
        this.tipoCarteira = tipoCarteira;
    }

    public String getEmail() {
        return email;
    }

    public String getTipoCarteiraString() {
        return tipoCarteira;
    }

    public TipoCarteira getTipoCarteiraEnum() {
        return TipoCarteira.valueOf(tipoCarteira.toUpperCase());
    }

    public Carteira toCarteira(String id, Cartao cartao){

        return new Carteira(id, this.email, cartao, this.tipoCarteira);
    }
}
