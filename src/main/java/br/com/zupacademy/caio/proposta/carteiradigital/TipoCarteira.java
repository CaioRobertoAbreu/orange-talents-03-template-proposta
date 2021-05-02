package br.com.zupacademy.caio.proposta.carteiradigital;

public enum TipoCarteira {

    PAYPAL, SAMSUNG_PAY;

    public void toEnum(String valor){

        TipoCarteira.valueOf(valor);

    }
}
