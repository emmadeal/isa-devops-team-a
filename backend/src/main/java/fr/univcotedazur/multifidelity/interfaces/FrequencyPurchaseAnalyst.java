package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.Purchase;
import fr.univcotedazur.multifidelity.entities.Society;

import java.util.List;

public interface FrequencyPurchaseAnalyst {  // changer nom

    boolean isVfp(Consumer consumer);

    List<Purchase> getAllPurchases();

    List<Purchase> getAllPurchasesBySociety(Society society);

    List<Purchase> getAllPurchasesByConsumer(Consumer consumer);

}
