package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.SelectedAdvantage;
import fr.univcotedazur.multifidelity.exceptions.NotAvailableAdvantageException;

public interface CollectGift {

    SelectedAdvantage giveGift(SelectedAdvantage selectedAdvantage) throws NotAvailableAdvantageException;
}
