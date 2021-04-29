package br.com.zupacademy.caio.proposta.cartao.viagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AvisoViagemRepository extends JpaRepository<AvisoViagem, Long> {
}
