package fr.univcotedazur.multifidelity.components;

import fr.univcotedazur.multifidelity.entities.Advantage;
import fr.univcotedazur.multifidelity.entities.SelectedAdvantage;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.entities.StateSelectedAdvantage;
import fr.univcotedazur.multifidelity.interfaces.AdvantageFinder;
import fr.univcotedazur.multifidelity.interfaces.AdvantageSelectedManagement;
import fr.univcotedazur.multifidelity.interfaces.CatalogModifier;
import fr.univcotedazur.multifidelity.repositories.AdvantageRepository;
import fr.univcotedazur.multifidelity.repositories.SelectedAdvantageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class AdvantageManager implements CatalogModifier, AdvantageSelectedManagement, AdvantageFinder{

    @Autowired
    private  AdvantageRepository advantageRepository;

    @Autowired
   private  SelectedAdvantageRepository selectedAdvantageRepository;

    @Override
    public Advantage addAdvantage(Advantage advantage) {
        return null;
        // add avantage
    }


    @Override
    public void deleteAdvantage(Advantage advantage) {
        // supp avantage
    }

    @Override
    public SelectedAdvantage addSelectAdvantage(SelectedAdvantage selectAdvantage) {
        return null;
        // ajoute UsedAdvantage avec etat AVAILABLE
        // ajouter 1 nb use sur avantage
    }

    @Override
    public SelectedAdvantage setState(SelectedAdvantage selectedAdvantage,StateSelectedAdvantage stateSelectedAdvantage) {
        selectedAdvantage.setState(stateSelectedAdvantage);
        return selectedAdvantageRepository.save(selectedAdvantage,selectedAdvantage.getId());
    }

    @Override
    public SelectedAdvantage setLastUse(LocalDateTime lastUse) {
        return null;
    }

    @Override
    public Optional<Advantage> findAdvantageById(UUID id) {
        return advantageRepository.findById(id);
    }

    @Override
    public Optional<SelectedAdvantage> findSelectedAdvantageById(UUID id) {
        return selectedAdvantageRepository.findById(id);
    }

    @Override
    public List<Advantage> getAllAdvantages() {
        return StreamSupport.stream(advantageRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<SelectedAdvantage> getAllSelectedAdvantages() {
        return StreamSupport.stream(selectedAdvantageRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<Advantage> getAllAdvantageBySociety(Society society) {
        return StreamSupport.stream(advantageRepository.findAll().spliterator(), false).filter(advantage -> advantage.getSociety()==society).collect(Collectors.toList());
    }
}