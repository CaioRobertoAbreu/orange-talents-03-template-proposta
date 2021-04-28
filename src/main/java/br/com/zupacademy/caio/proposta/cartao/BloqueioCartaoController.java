package br.com.zupacademy.caio.proposta.cartao;

import br.com.zupacademy.caio.proposta.cartao.bloqueio.BloqueioCartao;
import br.com.zupacademy.caio.proposta.cartao.bloqueio.BloqueioCartaoRepository;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

@RestController
@Validated
public class BloqueioCartaoController {

    private final CartaoRepository cartaoRepository;
    private final BloqueioCartaoRepository bloqueioCartaoRepository;

    public BloqueioCartaoController(CartaoRepository cartaoRepository, BloqueioCartaoRepository bloqueioCartaoRepository) {
        this.cartaoRepository = cartaoRepository;
        this.bloqueioCartaoRepository = bloqueioCartaoRepository;
    }

    @PostMapping("/cartoes/{id}/bloqueio")
    @Transactional
    public ResponseEntity<?> bloqueiaCartao(@PathVariable @CreditCardNumber String id, HttpServletRequest request){

        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if(cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if(cartao.get().getCartaoBloqueado()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        BloqueioCartao bloqueioCartao = new BloqueioCartao(
                cartao.get(), request.getRemoteAddr(), request.getHeader("User-Agent"));

        bloqueioCartaoRepository.save(bloqueioCartao);
        cartao.get().bloquearCartao();
        cartaoRepository.save(cartao.get());

        return ResponseEntity.ok().build();

    }
}
