package br.com.zupacademy.caio.proposta.transactions;

import br.com.zupacademy.caio.proposta.proposta.Proposta;
import br.com.zupacademy.caio.proposta.proposta.PropostaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class TransactionProposta {

    private final PropostaRepository propostaRepository;

    public TransactionProposta(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @Transactional
    public void salvar(Proposta proposta) {
        propostaRepository.save(proposta);
    }

    @Transactional
    public List<Proposta> cartoesAFazer(){
        return propostaRepository.cartoesAFazer();
    }

    @Transactional
    public List<Proposta> verificaPropostaNaoProcessada() {
        return propostaRepository.findPropostaNaoProcessada();
    }
}
