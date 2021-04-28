package br.com.zupacademy.caio.proposta.cartao;

import br.com.zupacademy.caio.proposta.biometria.Biometria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private BigDecimal limite;
    @ElementCollection
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private Set<Biometria> biometrias = new HashSet<>();
    private boolean cartaoBloqueado;


    public Cartao(String id, LocalDateTime emitidoEm, String titular, BigDecimal limite) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
    }

    @Deprecated
    public Cartao() {
    }

    public void addBiometria(Biometria biometria){
        this.biometrias.add(biometria);
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public boolean getCartaoBloqueado(){
        return this.cartaoBloqueado;
    }

    public void bloquearCartao(){
        this.cartaoBloqueado = true;
    }
}
