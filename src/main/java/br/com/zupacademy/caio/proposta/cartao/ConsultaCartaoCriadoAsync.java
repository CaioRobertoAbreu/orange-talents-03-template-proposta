package br.com.zupacademy.caio.proposta.cartao;

import br.com.zupacademy.caio.proposta.externo.cartao.ConsultaCartaoCriado;
import br.com.zupacademy.caio.proposta.externo.cartao.ConsultaCartaoRequestFeign;
import br.com.zupacademy.caio.proposta.proposta.Proposta;
import br.com.zupacademy.caio.proposta.transactions.TransactionProposta;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
public class ConsultaCartaoCriadoAsync {

    private final ConsultaCartaoCriado consultaCartaoCriado;
    private final TransactionProposta transaction;

    public ConsultaCartaoCriadoAsync(ConsultaCartaoCriado consultaCartaoCriado, TransactionProposta transaction) {
        this.consultaCartaoCriado = consultaCartaoCriado;
        this.transaction = transaction;
    }

    @Scheduled(fixedRateString = "${SCHEDULED_FIXED_RATE}", initialDelayString = "${SCHEDULED_INITIAL_DELAY}")
    public void consultarCartoes(){

        List<Proposta> propostas = transaction.cartoesAFazer();

        if(!propostas.isEmpty()){

            propostas.forEach(p -> {
                Cartao cartao = consultaCartaoCriado.verifica(new ConsultaCartaoRequestFeign(p));

                p.addCartao(cartao);
                transaction.salvar(p);

            });
        }
    }

}
