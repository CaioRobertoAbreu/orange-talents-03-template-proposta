package br.com.zupacademy.caio.proposta.cartao;

import br.com.zupacademy.caio.proposta.externo.cartao.ConsultaCartao;
import br.com.zupacademy.caio.proposta.externo.cartao.ConsultaCartaoRequestFeign;
import br.com.zupacademy.caio.proposta.proposta.Proposta;
import br.com.zupacademy.caio.proposta.transactions.TransactionProposta;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultaCartaoCriadoAsync {

    private final ConsultaCartao consultaCartao;
    private final TransactionProposta transaction;

    public ConsultaCartaoCriadoAsync(ConsultaCartao consultaCartao, TransactionProposta transaction) {
        this.consultaCartao = consultaCartao;
        this.transaction = transaction;
    }

    @Scheduled(fixedRateString = "${SCHEDULED_FIXED_RATE}", initialDelayString = "${SCHEDULED_INITIAL_DELAY}")
    public void consultarCartoes(){

        List<Proposta> propostas = transaction.cartoesAFazer();

        if(!propostas.isEmpty()){

            propostas.forEach(p -> {
                Cartao cartao = consultaCartao.verifica(new ConsultaCartaoRequestFeign(p));

                p.addCartao(cartao);
                transaction.salvar(p);

            });
        }
    }

}
