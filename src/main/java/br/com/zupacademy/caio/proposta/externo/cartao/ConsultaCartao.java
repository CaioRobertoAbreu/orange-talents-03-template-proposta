package br.com.zupacademy.caio.proposta.externo.cartao;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import br.com.zupacademy.caio.proposta.externo.cartao.bloqueio.BloqueioRequestFeign;
import br.com.zupacademy.caio.proposta.log.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException.FeignClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class ConsultaCartao {

    private final ObjectMapper mapper;
    private final ConsultaCartaoFeign feign;
    private final Logger log = LoggerFactory.getLogger(Log.class);

    public ConsultaCartao(ObjectMapper mapper, ConsultaCartaoFeign feign) {
        this.mapper = mapper;
        this.feign = feign;
    }

    public Cartao verifica(@Valid ConsultaCartaoRequestFeign request) {
        try{
            ConsultaCartaoResponseFeign response = feign.consultarCartaoCriado(request);
            log.info("Nova consulta realizada. Cartão processado nome={}", response.getTitular());

            return response.toCartao();

        } catch (FeignClientException e){
            log.info("Nova consulta realizada. Cartão não processado nome={}", request.getNome());
            return null;
        }
    }

    public Boolean bloqueiaCartao(String idCartao){
        BloqueioRequestFeign requestFeign = new BloqueioRequestFeign();
        try {
            feign.bloquearCartao(idCartao, requestFeign);
            log.info("Cartao bloqueado " + idCartao);
            return true;

        }catch (FeignClientException e){
            log.warn("Bloqueio não processado. " + e.getMessage());
            return false;
        }
    }



}
