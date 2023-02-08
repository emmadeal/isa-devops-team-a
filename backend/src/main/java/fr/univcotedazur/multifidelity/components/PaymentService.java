package fr.univcotedazur.multifidelity.components;

import fr.univcotedazur.multifidelity.entities.Card;
import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.Purchase;
import fr.univcotedazur.multifidelity.exceptions.BankRefusedPaymentException;
import fr.univcotedazur.multifidelity.exceptions.NotEnoughBalanceException;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.BalanceManagement;
import fr.univcotedazur.multifidelity.interfaces.CardFinder;
import fr.univcotedazur.multifidelity.interfaces.CardGetter;
import fr.univcotedazur.multifidelity.interfaces.CardUsage;
import fr.univcotedazur.multifidelity.interfaces.PointManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentService implements CardUsage, CardGetter {

    @Autowired
    private  BalanceManagement balanceManagement;

    @Autowired
    private  PointManagement pointManagement;

    @Autowired
    private  PurchaseManager purchaseManager;

    @Autowired
    private CardFinder cardFinder;

    @Override
    public Purchase buyInSocietyWithFidelityCard(Purchase purchase) throws NotEnoughBalanceException {
        // verifie assez de solde avec balanceManagement
        // supp solde  avec balanceManagement
        // add point avec pointManagement
        // add purchase  avec purchaseManager
        return null;
    }

    @Override
    public Purchase buyInSociety(Purchase purchase) {
        // add point avec pointManagement
        // add purchase  avec purchaseManager
        return null;
    }


    @Override
    public float reloadBalance(float amount, Consumer consumer) throws BankRefusedPaymentException {
        return 0.0F;
        // ajoute solde a la card en passant par balanceManagement et renvoi balance
    }

    @Override
    public Card getCardById(UUID id) throws ResourceNotFoundException {
        return cardFinder.findCardById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("No card found for id: %s", id.toString())));
    }
}
