package br.com.zupacademy.caio.proposta.transactions;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import br.com.zupacademy.caio.proposta.carteiradigital.CarteiraRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class TransactionCarteira {

    private final CarteiraRepository carteiraRepository;

    public TransactionCarteira(CarteiraRepository carteiraRepository) {
        this.carteiraRepository = carteiraRepository;
    }

    @Transactional
    public boolean existeCartao(Cartao cartao){
        Optional<Cartao> cartaoOptional = carteiraRepository.existeCarteira(cartao.getId());

        return cartaoOptional.isPresent();
    }
}
