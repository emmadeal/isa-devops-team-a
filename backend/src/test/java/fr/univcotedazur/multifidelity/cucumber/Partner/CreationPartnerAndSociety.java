package fr.univcotedazur.multifidelity.cucumber.Partner;

import fr.univcotedazur.multifidelity.entities.Partner;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistRessourceException;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistingAccountException;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.PartnerAccountManager;
import fr.univcotedazur.multifidelity.interfaces.PartnerGetter;
import fr.univcotedazur.multifidelity.interfaces.SocietyGetter;
import fr.univcotedazur.multifidelity.interfaces.SocietyModifier;
import fr.univcotedazur.multifidelity.repositories.PartnerRepository;
import fr.univcotedazur.multifidelity.repositories.SocietyRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.UUID;

public class CreationPartnerAndSociety {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private SocietyRepository societyRepository;

    @Autowired
    private PartnerAccountManager partnerAccountManager;

    @Autowired
    private SocietyModifier societyModifier;

    @Autowired
    private PartnerGetter partnerGetter;

    @Autowired
    private SocietyGetter societyGetter;

    Partner partner;
    Society society;

    @Before
    public void setUp() {
        partnerRepository.deleteAll();
        societyRepository.deleteAll();
        partner = null;
        society = null;
    }

    @When("the mayor creates a new partner {string} with email {string}")
    public void theMayorCreatesANewPartner(String arg0, String email) throws AlreadyExistingAccountException, AlreadyExistRessourceException {
        partner = new Partner(UUID.randomUUID(), arg0, email, "mdp");
        partnerAccountManager.addPartnerAccount(partner);
    }

    @Then("the partner {string} is present in the repository")
    public void thePartnerIsPresentInTheRepository(String arg0) throws ResourceNotFoundException {
        Assertions.assertTrue(partnerRepository.count() == 1);
        Partner p = partnerGetter.getPartnerById(partner.getId());
        Assertions.assertEquals(p, partner);
    }

    @Given("a partner {string}")
    public void aPartner(String arg0) {
        partner = new Partner(UUID.randomUUID(), arg0, "s@gmail.com", "mdp");
        partnerRepository.save(partner, partner.getId());
    }

    @When("the mayor creates a new society called {string} for the partner {string}")
    public void theMayorCreatesANewSocietyCalledForThePartner(String arg0, String arg1) throws AlreadyExistRessourceException {
        society = new Society(UUID.randomUUID(), arg0, partner, LocalTime.of(8, 0), LocalTime.of(18, 0), "1 rue Gambetta");
        societyModifier.addSociety(society);
    }

    @Then("the society is present in the repository")
    public void theSocietyIsPresentInTheRepository() throws ResourceNotFoundException {
        Assertions.assertTrue(societyRepository.count() == 1);
        Society s = societyGetter.getSocietyById(society.getId());
        Assertions.assertEquals(s, society);
    }

    @Then("the society is linked to the partner")
    public void theSocietyIsLinkedToThePartner() {
        Assertions.assertEquals(society.getOwner(), partner);
    }


    @Then("the mayor doesnt creates the new partner {string} with email {string}")
    public void theMayorDoesntCreatesTheNewPartner(String arg0, String email) throws AlreadyExistingAccountException, ResourceNotFoundException {
        Partner copieur = new Partner(UUID.randomUUID(), arg0, email, "mdp2");
        try {partnerAccountManager.addPartnerAccount(copieur);}
        catch (AlreadyExistingAccountException | AlreadyExistRessourceException e) {
            Assertions.assertEquals(partner, partnerGetter.getPartnerById(partner.getId()));
        }
    }

    @Then("the partner {string} is not present in the repository")
    public void thePartnerIsNotPresentInTheRepository(String arg0) {
        Assertions.assertTrue(partnerRepository.count() == 1);
    }

    @Then("the mayor creates a new partner {string} with email {string} and thie is failure")
    public void theMayorCreatesANewPartnerWithEmailAndThieIsFailure(String arg0, String arg1) {
        partner = new Partner(UUID.randomUUID(), arg0, arg1, "mdp");
        Assertions.assertThrows(AlreadyExistRessourceException.class, () ->  partnerAccountManager.addPartnerAccount(partner));

    }

    @Then("the mayor creates a new society called {string} for the partner {string} there is failure")
    public void theMayorCreatesANewSocietyCalledForThePartnerThereIsFailure(String arg0, String arg1) {
        society = new Society(UUID.randomUUID(), arg0, partner, LocalTime.of(8, 0), LocalTime.of(18, 0), "1 rue Gambetta");
        Assertions.assertThrows(AlreadyExistRessourceException.class, () -> societyModifier.addSociety(society));
    }
}