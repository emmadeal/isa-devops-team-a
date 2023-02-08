package fr.univcotedazur.multifidelity.cucumber.Consumer;

import fr.univcotedazur.multifidelity.components.ConsumerManager;
import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.exceptions.AccountNotFoundException;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistingAccountException;
import fr.univcotedazur.multifidelity.repositories.ConsumerRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginSignin {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ConsumerManager consumerManager;

    Consumer consumer;

    @Before
    public void setUp() {
        consumerRepository.deleteAll();
    }

    @Given("there is a consumer with email {string} and password {string} in database")
    public void consumer_with_email_and_password(String email, String password){
        consumer = new Consumer(email, password, null, null);
        consumerRepository.save(consumer, consumer.getId());
    }

    @When("the consumer signin")
    public void theConsumerSignin() {
        try {
            consumerManager.signIn(consumer.getEmail(), consumer.getPassword());
        } catch (AlreadyExistingAccountException e) {
            System.out.println(e.getMessage());
        }
    }

    @Then("there is {int} consumer in the database")
    public void thereIsConsumerInTheDatabase(int arg0) {
        Assertions.assertEquals(arg0, consumerRepository.count());
    }

    @When("a new consumer login with email {string} and password {string}")
    public void theNewConsumerLogin(String email, String password) {
        try {
            consumerManager.logIn(email, password);
        } catch(AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @When("a new consumer signin with email {string} and password {string}")
    public void theNewConsumerSignin(String email, String password) {
        try {
            consumerManager.signIn(email, password);
        } catch (AlreadyExistingAccountException e) {
            System.out.println(e.getMessage());
        }
    }
}
