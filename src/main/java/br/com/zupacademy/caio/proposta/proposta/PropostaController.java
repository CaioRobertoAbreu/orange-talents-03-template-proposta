package br.com.zupacademy.caio.proposta.proposta;

import br.com.zupacademy.caio.proposta.externo.solicitacao.Solicitacao;
import br.com.zupacademy.caio.proposta.externo.solicitacao.SolicitacaoRequest;
import br.com.zupacademy.caio.proposta.externo.solicitacao.VerificaDadosClienteFeign;
import br.com.zupacademy.caio.proposta.log.Log;
import br.com.zupacademy.caio.proposta.transactions.TransactionProposta;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.opentracing.Span;
import io.opentracing.Tracer;
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
    private final Tracer tracer;

    public PropostaController(VerificaDadosClienteFeign verificaDadosClienteFeign, ObjectMapper objectMapper,
                              TransactionProposta transaction, Tracer tracer) {
        this.verificaDadosClienteFeign = verificaDadosClienteFeign;
        this.objectMapper = objectMapper;
        this.transaction = transaction;
        this.tracer = tracer;
    }

    @PostMapping("/proposta")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaPropostaRequest request) {
        Span span = tracer.activeSpan();
        span.setTag("user.email", request.getEmail());
        Proposta proposta = request.toProposta();

        transaction.salvar(proposta);

        proposta.verificaDadosFinanceiros(verificaDadosClienteFeign, objectMapper);

        transaction.salvar(proposta);

        log.info("Proposta criada, email={}", proposta.getEmail());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}