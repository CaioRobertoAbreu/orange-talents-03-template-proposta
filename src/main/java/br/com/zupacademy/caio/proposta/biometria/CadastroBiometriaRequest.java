package br.com.zupacademy.caio.proposta.biometria;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import br.com.zupacademy.caio.proposta.customvalidation.VerifyFingerprint;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class CadastroBiometriaRequest {

    @NotBlank
    @VerifyFingerprint
    private String biometria;


    @JsonCreator
    public CadastroBiometriaRequest(String biometria) {
        this.biometria = biometria;
    }

    public String getBiometria() {
        return biometria;
    }

    public Biometria toBiometria(Cartao cartao) {

        return new Biometria(this.biometria, cartao);
    }
}
