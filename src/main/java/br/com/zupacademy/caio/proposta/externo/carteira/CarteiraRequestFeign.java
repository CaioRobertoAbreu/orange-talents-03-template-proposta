package br.com.zupacademy.caio.proposta.externo.carteira;

import br.com.zupacademy.caio.proposta.carteiradigital.PaypalRequest;

public class CarteiraRequestFeign {

    private String email;
    private String carteira;

    public CarteiraRequestFeign(PaypalRequest request) {
        this.email = request.getEmail();
        this.carteira = "paypal";
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
