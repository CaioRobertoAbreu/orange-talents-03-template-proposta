package br.com.zupacademy.caio.proposta.cartao.viagem;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String destino;
    @Column(nullable = false)
    @Future
    private LocalDate terminoViagem;
    @CreationTimestamp
    private LocalDateTime instante = LocalDateTime.now();
    @Column(nullable = false)
    private String ip;
    @Column(nullable = false)
    private String userAgent;
    @ManyToOne
    private Cartao catrtao;

    public AvisoViagem(String destino, LocalDate terminoViagem, String ip, String userAgent, Cartao cartao) {
        this.destino = destino;
        this.terminoViagem = terminoViagem;
        this.ip = ip;
        this.userAgent = userAgent;
        this.catrtao = cartao;
    }

}
