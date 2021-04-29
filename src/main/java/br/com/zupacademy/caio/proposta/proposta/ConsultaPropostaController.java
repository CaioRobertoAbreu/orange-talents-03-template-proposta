package br.com.zupacademy.caio.proposta.proposta;

import br.com.zupacademy.caio.proposta.metrics.MinhaMetricaTeste;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ConsultaPropostaController {

    private final PropostaRepository propostaRepository;
    private final MinhaMetricaTeste minhaMetricaTeste;

    public ConsultaPropostaController(PropostaRepository propostaRepository,
                                      MinhaMetricaTeste minhaMetricaTeste) {

        this.propostaRepository = propostaRepository;
        this.minhaMetricaTeste = minhaMetricaTeste;
    }

    @GetMapping("/proposta/{id}")
    @Transactional
    public ResponseEntity<?> consultarProposta(@PathVariable Long id) {

        Optional<Proposta> propostaOptional = propostaRepository.findById(id);

        if(propostaOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        minhaMetricaTeste.meuContador();
        Proposta proposta = propostaOptional.get();

        return ResponseEntity.ok(new PropostaResponse(proposta));

    }
}
