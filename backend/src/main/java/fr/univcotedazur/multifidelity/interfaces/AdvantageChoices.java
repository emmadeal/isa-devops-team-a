package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Advantage;
import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.SelectedAdvantage;
import fr.univcotedazur.multifidelity.exceptions.IWYPLSConnexionException;
import fr.univcotedazur.multifidelity.exceptions.NotEnoughPointsException;

public interface AdvantageChoices {

    SelectedAdvantage selectAdvantage(Advantage advantage, Consumer consumer) throws NotEnoughPointsException;


    SelectedAdvantage useAdvantage(Advantage advantage, Consumer consumer) throws IWYPLSConnexionException;

}
