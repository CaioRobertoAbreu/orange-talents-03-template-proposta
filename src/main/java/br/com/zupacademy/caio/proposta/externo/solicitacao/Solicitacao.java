package br.com.zupacademy.caio.proposta.externo.solicitacao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;

public class Solicitacao {

    private final VerificaDadosClienteFeign verificaDadosClienteFeign;
    private final ObjectMapper objectMapper;

    public Solicitacao(VerificaDadosClienteFeign verificaDadosClienteFeign, ObjectMapper objectMapper) {
        this.verificaDadosClienteFeign = verificaDadosClienteFeign;
        this.objectMapper = objectMapper;
    }

    public String verificaDados(SolicitacaoRequest request){
        try {
            SolicitacaoResponse response = verificaDadosClienteFeign.verifica(request);
            return response.getResultadoSolicitacao();

        }catch (FeignException e){
            String body = e.contentUTF8();
            try {
                /**
                 * Classe da biblioteca Jackson;
                 * O método readValue pode converter um texto para Objeto.
                 */
                SolicitacaoResponse solicitacaoResponse = objectMapper.readValue(body, SolicitacaoResponse.class);
                return solicitacaoResponse.getResultadoSolicitacao();
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        }

        return "";
    }
}
