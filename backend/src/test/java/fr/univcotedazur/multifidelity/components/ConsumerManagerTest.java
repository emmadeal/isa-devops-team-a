package fr.univcotedazur.multifidelity.components;

import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.exceptions.AccountNotFoundException;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistingAccountException;
import fr.univcotedazur.multifidelity.repositories.ConsumerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConsumerManagerTest {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ConsumerManager consumerManager;

    Consumer consumer;

    @BeforeEach
    public void setUpContext() {
        consumerRepository.deleteAll();
        consumer = new Consumer("email@gmail.com", "password", "9384758475845847", "CZ932GJ");
        consumerRepository.save(consumer, consumer.getId());
    }


    @Test
    public void newConsumerTryToSigninWithSameEmail(){
        Consumer newConsumer = new Consumer("email@gmail.com", "newPassword", null , null);
        AlreadyExistingAccountException e = Assertions.assertThrows(AlreadyExistingAccountException.class, () -> {
            Consumer check = consumerManager.signIn(newConsumer.getEmail(), newConsumer.getPassword());
            Assertions.assertEquals(1, consumerRepository.count());
            Assertions.assertEquals(newConsumer.getEmail(), check.getEmail());
            Assertions.assertEquals(newConsumer.getPassword(), check.getPassword());
        });
    }

    @Test
    public void newConsumerTryToLogin(){
        Consumer newConsumer = new Consumer("newEmail@gmail.com", "newPassword", null , null);
        AccountNotFoundException e = Assertions.assertThrows(AccountNotFoundException.class, () -> {
            Consumer check = consumerManager.logIn(newConsumer.getEmail(), newConsumer.getPassword());
            Assertions.assertEquals(1, consumerRepository.count());
            Assertions.assertEquals(newConsumer.getEmail(), check.getEmail());
            Assertions.assertEquals(newConsumer.getPassword(), check.getPassword());
        });
    }


    @Test
    public void newConsumerSignin(){
        Consumer newConsumer = new Consumer("newEmail@gmail.com", "newPassword", null , null);
        try {
            Consumer check = consumerManager.signIn(newConsumer.getEmail(), newConsumer.getPassword());
            Assertions.assertEquals(2, consumerRepository.count());
            Assertions.assertEquals(newConsumer.getEmail(), check.getEmail());
            Assertions.assertEquals(newConsumer.getPassword(), check.getPassword());
        } catch (AlreadyExistingAccountException e) {
            System.out.println(e.getMessage());
        };
    }


    @Test
    public void newConsumerLogin(){
        Consumer newConsumer = new Consumer("newEmail@gmail.com", "newPassword", null , null);
        try{
            Consumer check = consumerManager.logIn(newConsumer.getEmail(), newConsumer.getPassword());
            Assertions.assertEquals(2, consumerRepository.count());
            Assertions.assertEquals(newConsumer.getEmail(), check.getEmail());
            Assertions.assertEquals(newConsumer.getPassword(), check.getPassword());
        } catch(AccountNotFoundException e) {
            System.out.println(e.getMessage());
        };
    }
}
