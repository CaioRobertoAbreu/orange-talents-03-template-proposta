package br.com.zupacademy.caio.proposta.proposta;

import br.com.zupacademy.caio.proposta.cartao.CartaoResponse;

public class PropostaResponse {

    private String documento;
    private String nome;
    private String status;
    private CartaoResponse cartao;

    public PropostaResponse(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.status = proposta.getPropostaStatus();
        if(proposta.getCartao() != null) {
            this.cartao = new CartaoResponse(proposta.getCartao());
        }
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }

    public CartaoResponse getCartao() {
        return cartao;
    }
}
