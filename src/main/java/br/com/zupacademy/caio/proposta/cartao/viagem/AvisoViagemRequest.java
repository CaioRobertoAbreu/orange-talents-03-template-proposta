package br.com.zupacademy.caio.proposta.cartao.viagem;

import br.com.zupacademy.caio.proposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.*;

public class AvisoViagemRequest {

    @NotBlank
    private String destino;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = STRING)
    @Future
    @NotNull
    private LocalDate terminoViagem;

    public String getDestino() {
        return destino;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }

    public AvisoViagem toViagem(String ip, String userAgent, Cartao cartao){

        return new AvisoViagem(this.destino, this.terminoViagem, ip, userAgent, cartao);
    }
}
