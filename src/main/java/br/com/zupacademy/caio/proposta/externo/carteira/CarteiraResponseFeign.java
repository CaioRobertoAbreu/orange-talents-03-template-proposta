package br.com.zupacademy.caio.proposta.externo.carteira;

import br.com.zupacademy.caio.proposta.carteiradigital.AssociacaoCarteira;

public class CarteiraResponseFeign {

    private AssociacaoCarteira resultado;
    private String id;

    public AssociacaoCarteira getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
