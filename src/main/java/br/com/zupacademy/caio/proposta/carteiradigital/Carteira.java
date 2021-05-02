package br.com.zupacademy.caio.proposta.carteiradigital;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Carteira {

    @Id
    private String id;
    @Email
    @Column(nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private TipoCarteira tipoCarteira;
    @UniqueElements
    @ManyToMany
    private Set<Cartao> cartoes = new HashSet<>();

    @Deprecated
    public Carteira() {
    }

    public Carteira(String id, String email, Cartao cartao, String tipo) {
        this.id = id;
        this.email = email;
        this.cartoes.add(cartao);
        this.tipoCarteira = TipoCarteira.valueOf(tipo.toUpperCase());
    }


    public String getId() {
        return this.id;
    }
}
