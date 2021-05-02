package br.com.zupacademy.caio.proposta.cartao;

import br.com.zupacademy.caio.proposta.carteiradigital.TipoCarteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, String> {

    @Query("SELECT c FROM Carteira c " +
            "INNER JOIN c.cartoes cartao " +
            "WHERE cartao.id = :value AND c.tipoCarteira = :tipo")
    Optional<Cartao> existeCarteira(@Param(value = "value") String value,
                                    @Param(value = "tipo") TipoCarteira tipo);

}
