package br.com.zupacademy.caio.proposta.cartao.bloqueio;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime instante = LocalDateTime.now();
    @OneToOne
    private Cartao cartao;
    @Column(nullable = false)
    private String ipHost;
    @Column(nullable = false)
    private String userAgent;

    public BloqueioCartao() {
    }

    public BloqueioCartao(Cartao cartao, String ipHost, String userAgent) {
        this.cartao = cartao;
        this.ipHost = ipHost;
        this.userAgent = userAgent;
    }

}
