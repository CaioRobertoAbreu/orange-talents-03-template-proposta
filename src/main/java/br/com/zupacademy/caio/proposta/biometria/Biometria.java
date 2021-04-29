package br.com.zupacademy.caio.proposta.biometria;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private byte[] biometria;
    @CreationTimestamp
    private LocalDateTime dataCadastro = LocalDateTime.now();
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String biometria, Cartao cartao) {
        this.biometria = biometria.getBytes();
        this.cartao = cartao;
    }

    public Long getId() {
        return this.id;
    }
}
