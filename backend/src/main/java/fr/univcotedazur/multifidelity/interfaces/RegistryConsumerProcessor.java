package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.exceptions.AccountNotFoundException;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistingAccountException;

public interface RegistryConsumerProcessor {

    Consumer logIn(String email , String password) throws AccountNotFoundException;

    Consumer signIn(String email , String password) throws AlreadyExistingAccountException;
}
