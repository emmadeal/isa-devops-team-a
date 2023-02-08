package fr.univcotedazur.multifidelity.components;

import fr.univcotedazur.multifidelity.entities.Card;
import fr.univcotedazur.multifidelity.exceptions.BankRefusedPaymentException;
import fr.univcotedazur.multifidelity.exceptions.NegativeQuantityException;
import fr.univcotedazur.multifidelity.interfaces.BalanceManagement;
import fr.univcotedazur.multifidelity.interfaces.Bank;
import fr.univcotedazur.multifidelity.interfaces.CardFinder;
import fr.univcotedazur.multifidelity.interfaces.PointManagement;
import fr.univcotedazur.multifidelity.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CardManager implements BalanceManagement, PointManagement, CardFinder {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private  Bank bank;

    @Override
    public float updateBalance(float money, Card card) throws BankRefusedPaymentException {
        // supp sous sur card ou add sous selon le signe de money
        // renvoi les sous totale sur la balance
        return 0;

    }

    @Override
    public boolean canBuy(float amount, Card card) {
        return false;
        // verifie qu'il y est assez d'argent pour payer sur card
    }


    @Override
    public int updatePoints(int numberOfPoints, Card card)  throws NegativeQuantityException {
        // ajoute point sur card ou supp selon le sens de numberOfPoints
        // return le nb de point sur la carte
        return 0;
    }


    @Override
    public Optional<Card> findCardById(UUID id) {
        return cardRepository.findById(id);
    }
}
