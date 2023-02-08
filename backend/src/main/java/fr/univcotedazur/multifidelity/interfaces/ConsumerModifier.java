package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;

import java.util.UUID;

public interface ConsumerModifier {

    Consumer updateConsumer(UUID consumerId,Consumer newConsumer) throws ResourceNotFoundException;

    Consumer addFavSociety(Society society, Consumer consumer);

    Consumer deleteFavSociety(Society society, Consumer consumer);

}
