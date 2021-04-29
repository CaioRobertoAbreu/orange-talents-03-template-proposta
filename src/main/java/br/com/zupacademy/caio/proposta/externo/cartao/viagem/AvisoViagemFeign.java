package br.com.zupacademy.caio.proposta.externo.cartao.viagem;

import br.com.zupacademy.caio.proposta.cartao.viagem.AvisoViagemRequest;
import br.com.zupacademy.caio.proposta.externo.cartao.ConsultaCartaoFeign;
import br.com.zupacademy.caio.proposta.log.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AvisoViagemFeign {

    private final ConsultaCartaoFeign feign;
    private final ObjectMapper mapper;
    private final Logger log = LoggerFactory.getLogger(Log.class);

    public AvisoViagemFeign(ConsultaCartaoFeign feign, ObjectMapper mapper) {
        this.feign = feign;
        this.mapper = mapper;
    }

    public EnumAvisoViagem aviso(String id, AvisoViagemRequest request){
        ViagemRequestFeign requestFeign = new ViagemRequestFeign(request);
        AvisoViagemResponseFeign returnfeign;
        try {
            returnfeign = feign.avisoViagem(id, requestFeign);
            log.info("Aviso de viagem realizado com sucesso. " + id);
            return returnfeign.getResultado();

        }catch (FeignException.FeignClientException e){
            log.warn("Aviso de viagem falhou. " + e.getMessage());
            String body = e.contentUTF8();
            try{
                returnfeign = mapper.readValue(body, AvisoViagemResponseFeign.class);
                return returnfeign.getResultado();

            }catch (JsonProcessingException jsonProcessingException){
                return EnumAvisoViagem.FALHA;
            }
        }
    }
}
