package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public interface SocietiesInformationGetter {

    List<Society> getAllSocieties();

    Society getSocietyByName(String name) throws ResourceNotFoundException;


    Society getSocietyById(UUID societyId) throws ResourceNotFoundException;

}
