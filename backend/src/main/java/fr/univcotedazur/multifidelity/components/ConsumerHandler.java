package fr.univcotedazur.multifidelity.components;

import fr.univcotedazur.multifidelity.entities.Advantage;
import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.AccountNotFoundException;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistingAccountException;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.ConsumerAccountManager;
import fr.univcotedazur.multifidelity.interfaces.ConsumerFinder;
import fr.univcotedazur.multifidelity.interfaces.ConsumerGetter;
import fr.univcotedazur.multifidelity.interfaces.ConsumerInformationGiver;
import fr.univcotedazur.multifidelity.interfaces.ConsumerModifier;
import fr.univcotedazur.multifidelity.interfaces.FrequencyPurchaseAnalyst;
import fr.univcotedazur.multifidelity.interfaces.RegistryConsumerProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ConsumerHandler implements RegistryConsumerProcessor, ConsumerModifier, ConsumerInformationGiver, ConsumerGetter {

    @Autowired
    private ConsumerFinder consumerFinder;

    @Autowired
    private ConsumerAccountManager consumerAccountManager;

    @Autowired
    private FrequencyPurchaseAnalyst frequencyPurchaseAnalyst;


    @Override
    public Consumer logIn(String email, String password) throws AccountNotFoundException {
        // verifie que consumer existe grcae a consumerAccountManager

        try {
            if(!email.equals("") && ! password.equals(""))
                consumerAccountManager.signIn(email, password);
        } catch (AlreadyExistingAccountException e) {
            System.out.println(e.getMessage());
        }
        return new Consumer(email, password, null, null);
    }

    @Override
    public Consumer signIn(String email, String password) throws AlreadyExistingAccountException {
        // vverifie mot de passe et email correct (genre valeur conforme)
        // creer compte avec consumerAccountManager
        try {
            if(!email.equals("") && ! password.equals(""))
                consumerAccountManager.signIn(email, password);
        } catch (AlreadyExistingAccountException e) {
            System.out.println(e.getMessage());
        }
        return new Consumer(email, password, null, null);
    }

    @Override
    public Consumer updateConsumer(UUID consumerId,Consumer newConsumer) throws ResourceNotFoundException {
        return null;
        // veirfie correspondance entre consumerId et newConsumer.getId (sinon renvoi exception car c'est icoherent)
      // modifie avec consumerAccountManager
    }

    @Override
    public Consumer addFavSociety(Society society, Consumer consumer) {
        return null;
        // add shop to favorite shop avec consumerAccountManager
    }

    @Override
    public Consumer deleteFavSociety(Society society, Consumer consumer) {
        return null;
        // delete avec consumerAccountManager
    }


    @Override
    public Consumer getConsumerById(UUID id) throws ResourceNotFoundException {
        return consumerFinder.findConsumerById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("No consumer found for id: %s" , id.toString())));
    }

    @Override
    public List<Consumer> getAllConsumer() {
        return consumerFinder.getAllConsumer();
    }

    @Override
    public List<Society> getFavSocieties(Consumer consumer) throws ResourceNotFoundException {
        return consumerFinder.getFavSocieties(consumer);
    }


    private boolean IsVFP(Consumer consumer) {
        return false ;
        // veirfie si client est vfp grace a frequencyPurchaseAnalyst
    }


    public void VFPCheck() {
        // recupere tout les consumer (getAllConsumer)
        // pour chaque verifie si vfp et set
        // notifier consumer qui vont perdre status vfp avec reminderConsumers()
        // cron toute les heures
    }


    private void reminderConsumers() {
        // notifier consumer qui vont perdre status vfp
    }


    @Override
    public List<Advantage> getAllAdvantageEligible(Consumer consumer){
        return null;
    }


}
