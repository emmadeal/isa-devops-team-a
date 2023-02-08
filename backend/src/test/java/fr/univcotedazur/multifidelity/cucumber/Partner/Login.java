package fr.univcotedazur.multifidelity.cucumber.Partner;

import fr.univcotedazur.multifidelity.components.PartnerManager;
import fr.univcotedazur.multifidelity.entities.Partner;
import fr.univcotedazur.multifidelity.exceptions.AccountNotFoundException;
import fr.univcotedazur.multifidelity.repositories.PartnerRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class Login {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private PartnerManager partnerManager;

    private Partner partner;

    @Before
    public void setUp() {
        partnerRepository.deleteAll();
    }

    @Given("a partner named {string}, with email {string} and password {string}")
    public void aPartnerNamedWithEmailAndPassword(String arg0, String arg1, String arg2) {
        partner = Partner.builder().name(arg0).email(arg1).password(arg2).build();
    }

    @When("he sign in")
    public void heSignIn() {
        partnerRepository.save(partner, UUID.randomUUID());
    }

    @Then("the log in is sucessful")
    public void theLogInIsSucessful() throws AccountNotFoundException {
        assert partnerManager.login(partner.getEmail(), partner.getPassword()).equals(partner);
    }

    @Then("the log in unsucessful")
    public void theLogInUnsucessful() throws AccountNotFoundException {
        Assertions.assertThrows(AccountNotFoundException.class, () -> partnerManager.login(partner.getEmail(), partner.getPassword()));
    }
}
