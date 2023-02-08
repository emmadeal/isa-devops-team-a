package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Advantage;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistRessourceException;

public interface SocietyModifier {

    Society updateSocietyInformation(Society oldSociety,Society newSociety);

    Advantage addAdvantage(Advantage advantage);


    void deleteAdvantage(Advantage advantage);

    Society addSociety(Society newSociety) throws AlreadyExistRessourceException;

}

