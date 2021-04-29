package br.com.zupacademy.caio.proposta.cartao.viagem;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import br.com.zupacademy.caio.proposta.externo.cartao.viagem.AvisoViagemFeign;
import br.com.zupacademy.caio.proposta.externo.cartao.viagem.EnumAvisoViagem;
import br.com.zupacademy.caio.proposta.transactions.TransactionCartao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AvisoViagemController {

    private final AvisoViagemFeign avisoViagem;
    private final TransactionCartao transactionCartao;
    private final AvisoViagemRepository avisoViagemRepository;

    public AvisoViagemController(AvisoViagemFeign avisoViagemFeign, TransactionCartao transactionCartao,
                                 AvisoViagemRepository avisoViagemRepository) {

        this.avisoViagem = avisoViagemFeign;
        this.transactionCartao = transactionCartao;
        this.avisoViagemRepository = avisoViagemRepository;
    }

    @PostMapping("/cartao/{id}/avisoViagem")
    @Transactional
    public ResponseEntity<?> avisoViagem(@PathVariable String id, @RequestBody @Valid AvisoViagemRequest request, HttpServletRequest host){

        Optional<Cartao> cartao = transactionCartao.findById(id);

        if(cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if(avisoViagem.aviso(id, request).equals(EnumAvisoViagem.FALHA)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        AvisoViagem avisoViagem = request.toViagem(host.getRemoteAddr(), host.getHeader("User-Agent"), cartao.get());
        avisoViagemRepository.save(avisoViagem);

        return ResponseEntity.ok().build();
    }
}
