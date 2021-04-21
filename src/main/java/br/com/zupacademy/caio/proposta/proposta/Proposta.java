package br.com.zupacademy.caio.proposta.proposta;

import br.com.zupacademy.caio.proposta.externo.solicitacao.Solicitacao;
import br.com.zupacademy.caio.proposta.externo.solicitacao.SolicitacaoRequest;
import br.com.zupacademy.caio.proposta.externo.solicitacao.VerificaDadosClienteFeign;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String documento;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private PropostaStatus propostaStatus;

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public void verificaDadosFinanceiros(VerificaDadosClienteFeign feign, ObjectMapper objectMapper){
        Solicitacao solicitacao = new Solicitacao(feign, objectMapper);
        String status = solicitacao.verificaDados(new SolicitacaoRequest(this));

        this.propostaStatus = PropostaStatus.toEnum(status);
    }

    public void addStatus(String status){
        this.propostaStatus = PropostaStatus.toEnum(status);
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getDocumento() {
        return this.documento;
    }

    public String getNome() {
        return this.nome;
    }
}
