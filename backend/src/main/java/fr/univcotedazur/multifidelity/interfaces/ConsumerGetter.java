package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ConsumerGetter {

    Consumer getConsumerById(UUID id) throws ResourceNotFoundException;

    List<Consumer> getAllConsumer();
}
