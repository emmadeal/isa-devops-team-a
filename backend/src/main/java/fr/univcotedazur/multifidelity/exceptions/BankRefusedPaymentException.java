package fr.univcotedazur.multifidelity.exceptions;

public class BankRefusedPaymentException extends Exception {

    public BankRefusedPaymentException(String msg) {
        super(msg);
    }

}