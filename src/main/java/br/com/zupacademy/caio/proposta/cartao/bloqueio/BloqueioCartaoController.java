package br.com.zupacademy.caio.proposta.cartao.bloqueio;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import br.com.zupacademy.caio.proposta.cartao.bloqueio.Bloqueio;
import br.com.zupacademy.caio.proposta.cartao.bloqueio.BloqueioCartao;
import br.com.zupacademy.caio.proposta.cartao.bloqueio.BloqueioCartaoRepository;
import br.com.zupacademy.caio.proposta.exception.APIException;
import br.com.zupacademy.caio.proposta.externo.cartao.ConsultaCartao;
import br.com.zupacademy.caio.proposta.transactions.TransactionCartao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@Validated
public class BloqueioCartaoController {

    private final ConsultaCartao consultaCartao;
    private final TransactionCartao transactionCartao;
    private final BloqueioCartaoRepository bloqueioCartaoRepository;

    public BloqueioCartaoController(ConsultaCartao consultaCartao, TransactionCartao transactionCartao,
                                    BloqueioCartaoRepository bloqueioCartaoRepository) {
        this.consultaCartao = consultaCartao;
        this.transactionCartao = transactionCartao;
        this.bloqueioCartaoRepository = bloqueioCartaoRepository;
    }

    @PostMapping("/cartoes/{id}/bloqueio")
    /**@CreditCardNumber removido pois a API externa não retorna um nº valido**/
    @Transactional
    public ResponseEntity<?> bloqueiaCartao(@PathVariable String id, HttpServletRequest request){

        Optional<Cartao> cartao = transactionCartao.findById(id);

        if(cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if(cartao.get().getCartaoBloqueado().equals(Bloqueio.BLOQUEADO)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        if(consultaCartao.bloqueiaCartao(id)){
            bloqueiaCartao(cartao.get(), request);
        }

        return ResponseEntity.ok().build();
    }

    @Transactional
    private void bloqueiaCartao(Cartao cartao, HttpServletRequest request){
        BloqueioCartao bloqueioCartao = new BloqueioCartao(
                cartao, request.getRemoteAddr(), request.getHeader("User-Agent"));

        bloqueioCartaoRepository.save(bloqueioCartao);
        cartao.bloquearCartao();
        transactionCartao.salvar(cartao);

        ResponseEntity.ok().build();
    }
}
