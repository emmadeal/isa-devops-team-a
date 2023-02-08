package fr.univcotedazur.multifidelity.cucumber.ShowCase;

import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.Partner;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.interfaces.SocietiesInformationGetter;
import fr.univcotedazur.multifidelity.repositories.ConsumerRepository;
import fr.univcotedazur.multifidelity.repositories.PartnerRepository;
import fr.univcotedazur.multifidelity.repositories.SocietyRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ShowSocieties {

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    SocietyRepository societyRepository;

    @Autowired
    SocietiesInformationGetter societiesInformationGetter;

    List<Society> societyList;

    @Before
    public void setUp() {
        consumerRepository.deleteAll();
        partnerRepository.deleteAll();
        societyRepository.deleteAll();
        societyList = new ArrayList<>();
    }

    @Given("a client logged in as {string} with password {string}")
    public void aClientLoggedInAsWithPassword(String arg0, String arg1) {
        Consumer consumer = new Consumer(arg0, arg1, "123456789", "AB-123-CD");
        consumerRepository.save(consumer, consumer.getId());
    }

    @Given("a partner and his society named {string}")
    public void aPartnerAndHisSocietyNamed(String arg0) {
        Partner partner = new Partner();
        partnerRepository.save(partner, partner.getId());
        Society society = new Society();
        society.setOwner(partner);
        society.setName(arg0);
        societyRepository.save(society, society.getId());
    }

    @When("the client go to the society list")
    public void theClientGoToTheSocietyList() {
        societyList = societiesInformationGetter.getAllSocieties();
    }

    @Then("the client see {string}")
    public void theClientSee(String arg0) {
        Society soc = societiesInformationGetter.getAllSocieties().stream().findFirst().get();
        Assertions.assertTrue(societyList.contains(soc));
        Assertions.assertTrue(soc.getName().equals(arg0));
    }

    @Then("there is {int} society")
    public void thereIsSociety(int arg0) {
        Assertions.assertTrue(societyList.size() == arg0);
        Assertions.assertTrue(societyRepository.count()==arg0);
    }


}