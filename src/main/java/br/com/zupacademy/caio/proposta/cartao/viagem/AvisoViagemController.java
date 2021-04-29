package br.com.zupacademy.caio.proposta.cartao.viagem;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import br.com.zupacademy.caio.proposta.cartao.CartaoRepository;
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

    private final CartaoRepository cartaoRepository;
    private final AvisoViagemRepository avisoViagemRepository;

    public AvisoViagemController(CartaoRepository cartaoRepository, AvisoViagemRepository avisoViagemRepository) {
        this.cartaoRepository = cartaoRepository;
        this.avisoViagemRepository = avisoViagemRepository;
    }

    @PostMapping("/cartao/{id}/avisoViagem")
    @Transactional
    public ResponseEntity<?> avisoViagem(@PathVariable String id, @RequestBody @Valid AvisoViagemRequest request, HttpServletRequest host){

        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if(cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        AvisoViagem avisoViagem = request.toViagem(host.getRemoteAddr(), host.getHeader("User-Agent"), cartao.get());

        avisoViagemRepository.save(avisoViagem);

        return ResponseEntity.ok().build();
    }
}
