package br.com.zupacademy.caio.proposta.externo.cartao.bloqueio;

public class BloqueioRequestFeign {

    private final String sistemaResponsavel = "cartoes";

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
