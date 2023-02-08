package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;

import java.util.UUID;

public interface SocietyGetter {

    Society getSocietyById(UUID id) throws ResourceNotFoundException;

}
