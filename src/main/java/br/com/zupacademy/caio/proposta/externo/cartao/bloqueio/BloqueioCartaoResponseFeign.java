package br.com.zupacademy.caio.proposta.externo.cartao.bloqueio;

import br.com.zupacademy.caio.proposta.cartao.bloqueio.Bloqueio;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioCartaoResponseFeign {

    private Bloqueio resultado;

    @JsonCreator
    public BloqueioCartaoResponseFeign(Bloqueio resultado) {
        this.resultado = resultado;
    }

    public Bloqueio getResultado() {
        return resultado;
    }
}
