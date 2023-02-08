package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.Purchase;
import fr.univcotedazur.multifidelity.exceptions.BankRefusedPaymentException;
import fr.univcotedazur.multifidelity.exceptions.NotEnoughBalanceException;

public interface CardUsage {

    Purchase buyInSocietyWithFidelityCard(Purchase purchase) throws NotEnoughBalanceException;

    Purchase buyInSociety(Purchase purchase) ;

    float reloadBalance(float amount, Consumer consumer) throws BankRefusedPaymentException;

}

