package br.com.zupacademy.caio.proposta.biometria;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import br.com.zupacademy.caio.proposta.cartao.CartaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class BiometriaController {

    private final CartaoRepository cartaoRepository;
    private final BiometriaRepository biometriaRepository;


    public BiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.biometriaRepository = biometriaRepository;
    }

    @PostMapping("/cartoes/{id}/biometria")
    @Transactional
    public ResponseEntity<?> cadastrarBiomatria(@PathVariable String id, @RequestBody @Valid CadastroBiometriaRequest request){
        Optional<Cartao> cartaoOptional = cartaoRepository.findById(id);

        if(cartaoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = cartaoOptional.get();

        Biometria biometria = biometriaRepository.save(request.toBiometria(cartao));
        cartao.addBiometria(biometria);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/biometria/{id}")
                .buildAndExpand(biometria.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
