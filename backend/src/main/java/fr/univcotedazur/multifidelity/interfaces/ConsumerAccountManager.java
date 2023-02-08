package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.AccountNotFoundException;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistingAccountException;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;

import java.util.UUID;

public interface ConsumerAccountManager {


    Consumer logIn(String email , String password) throws AccountNotFoundException;

    Consumer signIn(String email , String password) throws AlreadyExistingAccountException;

    Consumer updateConsumer(UUID consumerId, Consumer newConsumer) throws ResourceNotFoundException;

    Consumer addFavSociety(Society society, Consumer consumer);

    Consumer deleteFavSociety(Society society, Consumer consumer);
}
