package br.com.zupacademy.caio.proposta.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class MinhaMetricaTeste {

    private final MeterRegistry meterRegistry;

    public MinhaMetricaTeste(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void meuContador(){

        Collection<Tag> tags = Arrays.asList(Tag.of("verificacao", "teste"));

        Counter contador = meterRegistry.counter("proposta_consultada", tags);
        contador.increment();
    }

}
