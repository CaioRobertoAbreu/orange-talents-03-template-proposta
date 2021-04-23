package br.com.zupacademy.caio.proposta.externo.cartao;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import br.com.zupacademy.caio.proposta.log.Log;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class ConsultaCartaoCriado {

    private final ConsultaCartaoCriadoFeign feign;
    private final Logger log = LoggerFactory.getLogger(Log.class);

    public ConsultaCartaoCriado(ConsultaCartaoCriadoFeign feign) {
        this.feign = feign;
    }

    public Cartao verifica(@Valid ConsultaCartaoRequestFeign request) {
        try{
            ConsultaCartaoResponseFeign response = feign.consultarCartaoCriado(request);
            log.info("Nova consulta realizada. Cartão processado nome={}", response.getTitular());

            return response.toCartao();

        } catch (RuntimeException e){
            log.info("Nova consulta realizada. Cartão não processado nome={}", request.getNome());
            return null;
        }
    }

}
