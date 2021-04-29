package br.com.zupacademy.caio.proposta.externo.cartao.viagem;

import br.com.zupacademy.caio.proposta.cartao.viagem.AvisoViagemRequest;

import java.time.LocalDate;

public class ViagemRequestFeign {

    private String destino;
    private LocalDate validoAte;

    public ViagemRequestFeign(AvisoViagemRequest request) {
        this.destino = request.getDestino();
        this.validoAte = request.getTerminoViagem();
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
