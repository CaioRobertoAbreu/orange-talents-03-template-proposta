package br.com.zupacademy.caio.proposta.cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Cartao {

    @NotBlank
    @Id
    private String id;
    @NotNull
    private LocalDateTime emitidoEm;
    @NotBlank
    private String titular;
    @NotNull
    private Integer limite;


    public Cartao(String id, LocalDateTime emitidoEm, String titular, Integer limite) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
    }

    @Deprecated
    public Cartao() {
    }

    public String getId() {
        return id;
    }
}
