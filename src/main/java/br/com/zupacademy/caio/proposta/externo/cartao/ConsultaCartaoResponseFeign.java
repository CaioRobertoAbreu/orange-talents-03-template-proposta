package br.com.zupacademy.caio.proposta.externo.cartao;

import br.com.zupacademy.caio.proposta.cartao.Cartao;

import java.time.LocalDateTime;

public class ConsultaCartaoResponseFeign {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private Integer limite;

    public ConsultaCartaoResponseFeign(String id, LocalDateTime emitidoEm, String titular, Integer limite) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
    }

    public String getTitular() {
        return titular;
    }

    public Cartao toCartao(){

        return new Cartao(this.id, this.emitidoEm, this.titular, this.limite);
    }
}
