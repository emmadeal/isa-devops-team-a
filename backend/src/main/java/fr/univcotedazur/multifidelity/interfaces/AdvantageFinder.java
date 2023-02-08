package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Advantage;
import fr.univcotedazur.multifidelity.entities.SelectedAdvantage;
import fr.univcotedazur.multifidelity.entities.Society;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdvantageFinder {

    Optional<Advantage> findAdvantageById(UUID id);

    Optional<SelectedAdvantage> findSelectedAdvantageById(UUID id);


    List<Advantage> getAllAdvantages();


    List<SelectedAdvantage> getAllSelectedAdvantages();

    List<Advantage> getAllAdvantageBySociety(Society society);
}
