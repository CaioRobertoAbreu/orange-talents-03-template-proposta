package br.com.zupacademy.caio.proposta.externo.carteira;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import br.com.zupacademy.caio.proposta.carteiradigital.Carteira;
import br.com.zupacademy.caio.proposta.carteiradigital.PaypalRequest;
import br.com.zupacademy.caio.proposta.log.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AssociaCarteira {

    private final CarteiraFeign feign;
    private final ObjectMapper mapper;
    private final Logger log = LoggerFactory.getLogger(Log.class);

    public AssociaCarteira(CarteiraFeign feign, ObjectMapper mapper) {
        this.feign = feign;
        this.mapper = mapper;
    }

    public Carteira associa(Cartao cartao, PaypalRequest request){
        CarteiraResponseFeign returnFeign;
        try {
            returnFeign = feign.associarCartao(cartao.getId(), new CarteiraRequestFeign(request));
            log.info("Nova associação realizada. " + cartao.getId());
            return request.toCarteira(returnFeign.getId(), cartao);

        }catch (FeignException e){
            log.warn("Falha ao realizar associação");
            return null;
        }
    }
}
