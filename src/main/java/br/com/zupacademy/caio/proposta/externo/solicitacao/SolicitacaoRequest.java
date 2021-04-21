package br.com.zupacademy.caio.proposta.externo.solicitacao;

import br.com.zupacademy.caio.proposta.proposta.Proposta;

public class SolicitacaoRequest {

    private String nome;
    private String documento;
    private String idProposta;

    public SolicitacaoRequest(Proposta proposta) {
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
        this.idProposta = proposta.getId().toString();
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
