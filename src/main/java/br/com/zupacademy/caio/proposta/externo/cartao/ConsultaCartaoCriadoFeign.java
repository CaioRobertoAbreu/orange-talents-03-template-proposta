package br.com.zupacademy.caio.proposta.externo.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(url = "${CARTOES_URL}", name = "consultaCartao")
public interface ConsultaCartaoCriadoFeign {

    @PostMapping
    ConsultaCartaoResponseFeign consultarCartaoCriado(@RequestBody ConsultaCartaoRequestFeign request);
}
