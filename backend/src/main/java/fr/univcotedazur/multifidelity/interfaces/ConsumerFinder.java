package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConsumerFinder {

    Optional<Consumer> findConsumerById(UUID id);

    List<Consumer> getAllConsumer();

    List<Society> getFavSocieties(Consumer consumer) throws ResourceNotFoundException;
}
