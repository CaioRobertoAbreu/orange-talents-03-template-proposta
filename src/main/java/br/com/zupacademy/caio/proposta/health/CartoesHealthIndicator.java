package br.com.zupacademy.caio.proposta.health;

import br.com.zupacademy.caio.proposta.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;

@Component
public class CartoesHealthIndicator implements HealthIndicator {

    private static final String URL = "http://localhost:8888/api/cartoes";
    private final Logger log = LoggerFactory.getLogger(Log.class);

    @Override
    public Health health() {
        try {
            Socket socket = new Socket(new URL(URL).getHost(), 8888);
            return Health.up().build();
        }catch (IOException e){
            log.warn("Não foi possível se conectar com a API {}", URL);
            return Health.down().build();
        }
    }
}
