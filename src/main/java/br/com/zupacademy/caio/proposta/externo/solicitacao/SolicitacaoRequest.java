package br.com.zupacademy.caio.proposta.externo.solicitacao;

import br.com.zupacademy.caio.proposta.proposta.Proposta;

import javax.validation.constraints.NotBlank;

public class SolicitacaoRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String documento;
    @NotBlank
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
