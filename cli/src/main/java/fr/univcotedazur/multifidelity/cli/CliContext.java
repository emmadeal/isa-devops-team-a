package fr.univcotedazur.multifidelity.cli;

import fr.univcotedazur.multifidelity.cli.model.CliConsumer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CliContext {

    private Map<String, CliConsumer> consumers;

    public Map<String, CliConsumer> getCustomers() {
        return consumers;
    }

    public CliContext() {
        consumers = new HashMap<>();
    }

    @Override
    public String toString() {
        return consumers.keySet().stream()
                .map(key -> key + "=" + consumers.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
    }

}
