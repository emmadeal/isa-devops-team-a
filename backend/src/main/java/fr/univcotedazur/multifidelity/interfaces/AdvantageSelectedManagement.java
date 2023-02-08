package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.SelectedAdvantage;
import fr.univcotedazur.multifidelity.entities.StateSelectedAdvantage;

import java.time.LocalDateTime;

public interface AdvantageSelectedManagement {

    SelectedAdvantage addSelectAdvantage(SelectedAdvantage selectedAdvantage);


    SelectedAdvantage setState(SelectedAdvantage selectedAdvantage,StateSelectedAdvantage stateSelectedAdvantage);


    SelectedAdvantage setLastUse(LocalDateTime lastUse);
}

