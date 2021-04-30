package br.com.zupacademy.caio.proposta.carteiradigital;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import br.com.zupacademy.caio.proposta.cartao.CartaoRepository;
import br.com.zupacademy.caio.proposta.externo.carteira.AssociaCarteira;
import br.com.zupacademy.caio.proposta.transactions.TransactionCartao;
import br.com.zupacademy.caio.proposta.transactions.TransactionCarteira;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class CarteiraController {

    private final TransactionCartao transactionCartao;
    private final TransactionCarteira transactionCarteira;
    private final AssociaCarteira associaCarteira;

    public CarteiraController(TransactionCartao transactionCartao, TransactionCarteira transactionCarteira,
                              AssociaCarteira associaCarteira) {
        this.transactionCartao = transactionCartao;
        this.transactionCarteira = transactionCarteira;
        this.associaCarteira = associaCarteira;
    }

    @PostMapping("/cartoes/{id}/paypal")
    @Transactional
    public ResponseEntity<?> criarCarteiraPaypal(@PathVariable String id, @RequestBody PaypalRequest request) {

        Optional<Cartao> cartaoOptional = transactionCartao.findById(id);

        if(cartaoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = cartaoOptional.get();

        if(transactionCarteira.existeCartao(cartao)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        cartao.addCarteira(associaCarteira, request);

        transactionCartao.salvar(cartao);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/carteiras/{id}")
                .buildAndExpand(cartao.getCarteira().getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
