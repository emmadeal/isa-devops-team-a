package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Card;
import fr.univcotedazur.multifidelity.entities.Purchase;
import fr.univcotedazur.multifidelity.entities.Society;

public interface PurchaseManagement {

    Purchase addPurchase(int numberOfPoints, Card card, Society society);
}

