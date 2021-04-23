package br.com.zupacademy.caio.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    @Query("SELECT p FROM Proposta p WHERE p.propostaStatus = 'elegivel' AND " +
            " p.cartao.id = NULL ")
    List<Proposta> cartoesAFazer();

    @Query("SELECT p FROM Proposta p WHERE p.propostaStatus = NULL")
    List<Proposta> findPropostaNaoProcessada();

}
