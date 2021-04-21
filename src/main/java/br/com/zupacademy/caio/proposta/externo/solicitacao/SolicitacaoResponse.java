package br.com.zupacademy.caio.proposta.externo.solicitacao;

public class SolicitacaoResponse {

    private String nome;
    private String documento;
    private String idProposta;
    private String resultadoSolicitacao;

    public SolicitacaoResponse(String nome, String documento, String idProposta, String resultadoSolicitacao) {
        this.nome = nome;
        this.documento = documento;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
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

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

}
