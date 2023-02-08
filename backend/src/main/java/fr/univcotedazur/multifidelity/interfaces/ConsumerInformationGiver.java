package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Advantage;
import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ConsumerInformationGiver {


   List<Society> getFavSocieties(Consumer consumer) throws ResourceNotFoundException;


   List<Advantage> getAllAdvantageEligible(Consumer consumer);


}

