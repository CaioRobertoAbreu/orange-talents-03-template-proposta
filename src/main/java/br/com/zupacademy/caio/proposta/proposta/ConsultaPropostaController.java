package br.com.zupacademy.caio.proposta.proposta;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ConsultaPropostaController {

    private final PropostaRepository propostaRepository;

    public ConsultaPropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @GetMapping("/propostas/{id}")
    @Transactional
    public ResponseEntity<?> consultarProposta(@PathVariable Long id) {

        Optional<Proposta> propostaOptional = propostaRepository.findById(id);

        if(propostaOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Proposta proposta = propostaOptional.get();

        return ResponseEntity.ok(new PropostaResponse(proposta));

    }
}
