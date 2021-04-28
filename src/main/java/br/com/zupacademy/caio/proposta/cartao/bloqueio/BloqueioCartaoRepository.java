package br.com.zupacademy.caio.proposta.cartao.bloqueio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioCartaoRepository extends JpaRepository<BloqueioCartao, Long> {
}
