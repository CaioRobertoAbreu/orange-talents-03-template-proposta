package br.com.zupacademy.caio.proposta.externo.cartao;

import br.com.zupacademy.caio.proposta.externo.cartao.bloqueio.BloqueioCartaoResponseFeign;
import br.com.zupacademy.caio.proposta.externo.cartao.bloqueio.BloqueioRequestFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${CARTOES_URL}", name = "consultaCartao")
public interface ConsultaCartaoFeign {

    @PostMapping
    ConsultaCartaoResponseFeign consultarCartaoCriado(@RequestBody ConsultaCartaoRequestFeign request);

    @PostMapping("/{id}/bloqueios")
    BloqueioCartaoResponseFeign bloquearCartao(@PathVariable String id, @RequestBody BloqueioRequestFeign request);
}
