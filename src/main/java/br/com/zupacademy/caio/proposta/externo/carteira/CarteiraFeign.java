package br.com.zupacademy.caio.proposta.externo.carteira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${CARTOES_URL}", name = "associaCartaoCarteira")
public interface CarteiraFeign {

    @PostMapping("${CARTEIRA_URL}")
    CarteiraResponseFeign associarCartao(@PathVariable String id, @RequestBody CarteiraRequestFeign request);
}
