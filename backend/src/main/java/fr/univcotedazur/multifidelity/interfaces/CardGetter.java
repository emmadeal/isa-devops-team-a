package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Card;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;

import java.util.UUID;

public interface CardGetter {

    Card getCardById(UUID id) throws ResourceNotFoundException;
}
