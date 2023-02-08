package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Advantage;

public interface AdvantageModifier {

    Advantage addAdvantage(Advantage advantage);


    void deleteAdvantage(Advantage advantage);
}
