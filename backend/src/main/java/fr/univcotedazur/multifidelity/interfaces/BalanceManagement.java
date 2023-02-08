package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Card;
import fr.univcotedazur.multifidelity.exceptions.BankRefusedPaymentException;

public interface BalanceManagement {

    float updateBalance(float money, Card card) throws BankRefusedPaymentException;

    boolean canBuy(float amount, Card card);
}
