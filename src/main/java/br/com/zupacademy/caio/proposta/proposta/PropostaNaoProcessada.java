package br.com.zupacademy.caio.proposta.proposta;

import br.com.zupacademy.caio.proposta.externo.solicitacao.VerificaDadosClienteFeign;
import br.com.zupacademy.caio.proposta.transactions.TransactionProposta;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PropostaNaoProcessada {

    private final TransactionProposta transaction;
    private final VerificaDadosClienteFeign verificaDadosClienteFeign;
    private final ObjectMapper objectMapper;

    public PropostaNaoProcessada(TransactionProposta transaction, VerificaDadosClienteFeign verificaDadosClienteFeign,
                                 ObjectMapper objectMapper) {
        this.transaction = transaction;
        this.verificaDadosClienteFeign = verificaDadosClienteFeign;
        this.objectMapper = objectMapper;
    }

    @Scheduled(fixedRateString = "${SCHEDULED_FIXED_RATE}", initialDelayString = "${SCHEDULED_INITIAL_DELAY}")
    public void verificaPropostasNaoProcessadas(){

        List<Proposta> propostas = transaction.verificaPropostaNaoProcessada();

        if (!propostas.isEmpty()){
            propostas.forEach(p -> {
                p.verificaDadosFinanceiros(verificaDadosClienteFeign, objectMapper);
                transaction.salvar(p);
            });
        }
    }
}
