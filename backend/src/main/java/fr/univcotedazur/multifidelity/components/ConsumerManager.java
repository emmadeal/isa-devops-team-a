package fr.univcotedazur.multifidelity.components;

import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.ScheduleType;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.AccountNotFoundException;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistingAccountException;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.ConsumerAccountManager;
import fr.univcotedazur.multifidelity.interfaces.ConsumerFinder;
import fr.univcotedazur.multifidelity.interfaces.NotificationSender;
import fr.univcotedazur.multifidelity.repositories.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ConsumerManager implements ConsumerFinder, ConsumerAccountManager, NotificationSender {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Override
    public Optional<Consumer> findConsumerById(UUID id) {
        return consumerRepository.findById(id);
    }

    @Override
    public Consumer logIn(String email, String password) throws AccountNotFoundException {
        Optional<Consumer> consumer = StreamSupport.stream(consumerRepository.findAll().spliterator(), false)
                .filter(c -> c.getEmail().equals(email) && c.getPassword().equals(password)).findAny();

        if(consumer.isPresent())
            return consumer.get();
        else
            throw new AccountNotFoundException(email);
    }

    @Override
    public Consumer signIn(String email, String password) throws AlreadyExistingAccountException {
        // creer nouveau compte en verifiant email non utlise

        Optional<Consumer> consumer = StreamSupport.stream(consumerRepository.findAll().spliterator(), false)
                .filter(c -> c.getEmail().equals(email)).findAny();

        if(consumer.isPresent())
            throw new AlreadyExistingAccountException(email);
        else{
            Consumer newConsumer = new Consumer(email, password, null, null);
            newConsumer.setId(UUID.randomUUID());
            consumerRepository.save(newConsumer, newConsumer.getId());
            return newConsumer;
        }
    }

    @Override
    public Consumer updateConsumer(UUID consumerId, Consumer newConsumer) throws ResourceNotFoundException {
        return null;
        //modifie les infos du consumer avec consumerId
        // avec les info modifier dans newConsumer
    }

    @Override
    public Consumer addFavSociety(Society society, Consumer consumer) {
        consumer.getFavSocieties().add(society);
        return consumer;
    }

    @Override
    public Consumer deleteFavSociety(Society society, Consumer consumer) {
        consumer.getFavSocieties().remove(society);
        return consumer;
    }


    @Override
    public void notifyConsumerNewSchedule(LocalTime schedule, Society shop, ScheduleType scheduleType) {
        List<Consumer> consumersToNotify = getAllConsumer().stream().filter(consumer -> consumer.getFavSocieties().contains(shop)).toList();
        //TODOO ajouter le fait de notifier
    }

    @Override
    public List<Consumer> getAllConsumer() {
        return StreamSupport.stream(consumerRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<Society> getFavSocieties(Consumer consumer) {
        return consumer.getFavSocieties();
    }


}
