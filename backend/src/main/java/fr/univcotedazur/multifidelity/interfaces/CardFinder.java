package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Card;

import java.util.Optional;
import java.util.UUID;


public interface CardFinder {

    Optional<Card> findCardById(UUID id);
}
