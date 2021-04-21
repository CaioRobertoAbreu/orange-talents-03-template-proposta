package br.com.zupacademy.caio.proposta.proposta;

public enum PropostaStatus {

    NAO_ELEGIVEL,
    ELEGIVEL;

    public static PropostaStatus toEnum(String status){
        if(status.equalsIgnoreCase("com_restricao")){
            return NAO_ELEGIVEL;
        }

        if(status.equalsIgnoreCase("sem_restricao")){
            return ELEGIVEL;
        }

        throw new IllegalArgumentException("Não existe enum com esse valor");
    }

}
