package br.com.zupacademy.caio.proposta.transactions;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import br.com.zupacademy.caio.proposta.cartao.CartaoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class TransactionCartao {

    private final CartaoRepository cartaoRepository;

    public TransactionCartao(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @Transactional
    public Optional<Cartao> findById(String id) {
        return cartaoRepository.findById(id);
    }

    @Transactional
    public Cartao salvar(Cartao cartao){
        return cartaoRepository.save(cartao);
    }

}
