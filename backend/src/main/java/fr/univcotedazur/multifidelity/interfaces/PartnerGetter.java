package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Partner;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;

import java.util.UUID;

public interface PartnerGetter {
    Partner getPartnerById(UUID id) throws ResourceNotFoundException;
}
