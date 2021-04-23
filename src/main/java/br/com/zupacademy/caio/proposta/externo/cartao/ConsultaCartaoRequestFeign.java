package br.com.zupacademy.caio.proposta.externo.cartao;

import br.com.zupacademy.caio.proposta.proposta.Proposta;

import javax.validation.constraints.NotBlank;

public class ConsultaCartaoRequestFeign {

    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank
    private String idProposta;

    public ConsultaCartaoRequestFeign(Proposta proposta) {
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
