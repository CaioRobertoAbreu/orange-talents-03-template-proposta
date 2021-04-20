package br.com.zupacademy.caio.proposta.proposta;

import br.com.zupacademy.caio.proposta.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final PropostaRepository propostaRepository;
    private final Logger log = LoggerFactory.getLogger(Log.class);

    public PropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @PostMapping("/proposta")
    @Transactional
    public ResponseEntity<?> index(@RequestBody @Valid NovaPropostaRequest request) {
        Proposta proposta = request.toProposta();
        propostaRepository.save(proposta);

        log.info("Proposta criada, email={}", proposta.getEmail());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
