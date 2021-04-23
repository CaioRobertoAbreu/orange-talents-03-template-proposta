package br.com.zupacademy.caio.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartaoResponse {

    private String finalCartao;
    private LocalDateTime emitidoEm;
    private BigDecimal limite;

    public CartaoResponse(Cartao cartao) {
        if(cartao != null){
            this.emitidoEm = cartao.getEmitidoEm();
            this.limite = cartao.getLimite();
            this.finalCartao = cartao.getId().substring(15);
        }
    }

    public String getFinalCartao() {
        return finalCartao;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public BigDecimal getLimite() {
        return limite;
    }
}
