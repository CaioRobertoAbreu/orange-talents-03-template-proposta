package br.com.zupacademy.caio.proposta.health;

import br.com.zupacademy.caio.proposta.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

@Component("solicitacao")
public class SolicitacaoHealthIndicator implements HealthIndicator {

    private static final String URL = "http://localhost:9999/api/solicitacao";
    private final Logger log = LoggerFactory.getLogger(Log.class);

    @Override
    public Health health() {

        try {
            Socket socket = new Socket(new URL(URL).getHost(), 9999);
            return Health.up().build();

        } catch (IOException e) {
            log.warn("Não foi possível se conectar com a API {}", URL);
            return Health.down().build();
        }

    }
}
