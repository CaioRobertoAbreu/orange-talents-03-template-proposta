package br.com.zupacademy.caio.proposta.externo.carteira;

import br.com.zupacademy.caio.proposta.carteiradigital.CarteiraRequest;

public class CarteiraRequestFeign {

    private String email;
    private String carteira;

    public CarteiraRequestFeign(CarteiraRequest request) {
        this.email = request.getEmail();
        this.carteira = request.getTipoCarteiraString();
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
