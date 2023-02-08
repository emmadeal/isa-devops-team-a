package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.exceptions.BankRefusedPaymentException;

public interface Bank {
    boolean importMoney(Consumer consumer, float value) ;

}
