package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Advantage;

import java.util.UUID;

public interface CatalogModifier {

    Advantage addAdvantage(Advantage advantage);


    void deleteAdvantage(Advantage advantage);
}
