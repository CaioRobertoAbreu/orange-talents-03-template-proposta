package br.com.zupacademy.caio.proposta.proposta;

import br.com.zupacademy.caio.proposta.externo.solicitacao.Solicitacao;
import br.com.zupacademy.caio.proposta.externo.solicitacao.SolicitacaoRequest;
import br.com.zupacademy.caio.proposta.externo.solicitacao.VerificaDadosClienteFeign;
import br.com.zupacademy.caio.proposta.log.Log;
import br.com.zupacademy.caio.proposta.transactions.TransactionProposta;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class PropostaController {

    private final VerificaDadosClienteFeign verificaDadosClienteFeign;
    private final ObjectMapper objectMapper;
    private final TransactionProposta transaction;
    private final Logger log = LoggerFactory.getLogger(Log.class);

    public PropostaController(VerificaDadosClienteFeign verificaDadosClienteFeign,
                              ObjectMapper objectMapper, TransactionProposta transaction) {

        this.verificaDadosClienteFeign = verificaDadosClienteFeign;
        this.objectMapper = objectMapper;
        this.transaction = transaction;
    }

    @PostMapping("/proposta")
    @Transactional
    public ResponseEntity<?> index(@RequestBody @Valid NovaPropostaRequest request) {
        Proposta proposta = request.toProposta();

        propostaRepository.save(proposta);
        proposta.verificaDadosFinanceiros(verificaDadosClienteFeign, objectMapper);

        log.info("Proposta criada, email={}", proposta.getEmail());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}