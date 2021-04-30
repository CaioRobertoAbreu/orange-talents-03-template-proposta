package br.com.zupacademy.caio.proposta.cartao;

import br.com.zupacademy.caio.proposta.carteiradigital.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, String> {

}
