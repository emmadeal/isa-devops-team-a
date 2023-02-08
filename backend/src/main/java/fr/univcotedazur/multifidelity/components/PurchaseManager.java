package fr.univcotedazur.multifidelity.components;

import fr.univcotedazur.multifidelity.entities.Card;
import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.Purchase;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.interfaces.FrequencyPurchaseAnalyst;
import fr.univcotedazur.multifidelity.interfaces.PurchaseManagement;
import fr.univcotedazur.multifidelity.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PurchaseManager implements PurchaseManagement, FrequencyPurchaseAnalyst {

    @Autowired
    private PurchaseRepository purchaseRepository;


    @Override
    public Purchase addPurchase(int numberOfPoints, Card card, Society society) {
        //add la vente
        return null;
    }

    @Override
    public boolean isVfp(Consumer consumer) {
        // trouver card
        // regarde dans repo purchase si plus de 5 achat dans la semaine
        return false;
    }


    @Override
    public List<Purchase> getAllPurchases() {
        return StreamSupport.stream(purchaseRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<Purchase> getAllPurchasesBySociety(Society society) {
        return StreamSupport.stream(purchaseRepository.findAll().spliterator(), false).filter(purchase -> purchase.getSociety()==society).collect(Collectors.toList());
    }

    @Override
    public List<Purchase> getAllPurchasesByConsumer(Consumer consumer) {
        return StreamSupport.stream(purchaseRepository.findAll().spliterator(), false).filter(purchase -> purchase.getCard().getOwner()==consumer).collect(Collectors.toList());
    }
}
