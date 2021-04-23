package br.com.zupacademy.caio.proposta.externo.solicitacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${PROPOSTA_URL}", name = "verificaDados")
@Component
public interface VerificaDadosClienteFeign {

    @PostMapping(consumes = "application/json")
    SolicitacaoResponse verifica(@RequestBody SolicitacaoRequest request);
}
