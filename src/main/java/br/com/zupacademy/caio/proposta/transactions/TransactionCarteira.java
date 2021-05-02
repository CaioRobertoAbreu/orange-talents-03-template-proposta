package br.com.zupacademy.caio.proposta.transactions;

import br.com.zupacademy.caio.proposta.carteiradigital.Carteira;
import br.com.zupacademy.caio.proposta.carteiradigital.CarteiraRepository;
import org.springframework.stereotype.Component;

@Component
public class TransactionCarteira {

    private final CarteiraRepository carteiraRepository;

    public TransactionCarteira(CarteiraRepository carteiraRepository) {
        this.carteiraRepository = carteiraRepository;
    }

    public Carteira salvar(Carteira carteira){
        return carteiraRepository.save(carteira);
    }

}
