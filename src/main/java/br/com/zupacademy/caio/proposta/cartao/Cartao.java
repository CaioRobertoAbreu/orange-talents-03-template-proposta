package br.com.zupacademy.caio.proposta.cartao;

import br.com.zupacademy.caio.proposta.biometria.Biometria;
import br.com.zupacademy.caio.proposta.cartao.bloqueio.Bloqueio;
import br.com.zupacademy.caio.proposta.carteiradigital.Carteira;
import br.com.zupacademy.caio.proposta.carteiradigital.CarteiraRequest;
import br.com.zupacademy.caio.proposta.externo.carteira.AssociaCarteira;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

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
    @Enumerated(EnumType.STRING)
    private Bloqueio cartaoBloqueado = Bloqueio.NAO_BLOQUEADO;
    @ManyToMany(mappedBy = "cartoes", cascade = CascadeType.ALL)
    private List<Carteira> carteiras = new ArrayList<>();

    public Cartao(String id, LocalDateTime emitidoEm, String titular, BigDecimal limite) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
    }

    @Deprecated
    public Cartao() {
    }

    public void addCarteira(Carteira carteira){
        this.carteiras.add(carteira);
    }

    public Collection<Carteira> getCarteiras() {
        return carteiras;
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

    public Enum<Bloqueio> getCartaoBloqueado() {
        return this.cartaoBloqueado;
    }

    public void bloquearCartao(){
        this.cartaoBloqueado = Bloqueio.BLOQUEADO;
    }
}
