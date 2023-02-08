package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Advantage;
import fr.univcotedazur.multifidelity.entities.SelectedAdvantage;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public interface AdvantageGetter {

    Advantage getAdvantageById(UUID id) throws ResourceNotFoundException;

    SelectedAdvantage getSelectedAdvantageById(UUID id) throws ResourceNotFoundException;

    List<Advantage> getAllAdvantages();


    List<SelectedAdvantage> getAllSelectedAdvantages();

    List<Advantage> getAllAdvantageBySociety(Society society) ;
}
