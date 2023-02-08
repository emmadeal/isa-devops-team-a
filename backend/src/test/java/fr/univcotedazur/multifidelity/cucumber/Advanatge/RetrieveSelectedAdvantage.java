package fr.univcotedazur.multifidelity.cucumber.Advanatge;

import fr.univcotedazur.multifidelity.entities.Advantage;
import fr.univcotedazur.multifidelity.entities.Card;
import fr.univcotedazur.multifidelity.entities.Partner;
import fr.univcotedazur.multifidelity.entities.SelectedAdvantage;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.entities.StateSelectedAdvantage;
import fr.univcotedazur.multifidelity.exceptions.NotAvailableAdvantageException;
import fr.univcotedazur.multifidelity.interfaces.CollectGift;
import fr.univcotedazur.multifidelity.repositories.SelectedAdvantageRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.UUID;

@SpringBootTest
public class RetrieveSelectedAdvantage {

    @Autowired
    private CollectGift collectGift;


    @Autowired
    private SelectedAdvantageRepository selectedAdvantageRepository;

    Society society;
    Advantage advantage;

    SelectedAdvantage selectedAdvantage;

    @Before
    public void setUp() {
        selectedAdvantageRepository.deleteAll();
    }


    @Given("a society name {string}")
    public void a_society_name(String string) {
        society = new Society(UUID.randomUUID(), string, new Partner(), LocalTime.NOON, LocalTime.MIDNIGHT, "8 rue des fleurs");
    }
    @Given("a Advantage avaible at the same shop")
    public void a_advantage_avaible_at_the_same_shop() {
        advantage = new Advantage();
        advantage.setSociety(society);
    }
    @Given("a customer who is choosing an advantage from the shop")
    public void a_customer_who_is_choosing_an_advantage_from_the_shop() {
        selectedAdvantage = new SelectedAdvantage(UUID.randomUUID(), StateSelectedAdvantage.AVAILABLE, new Card(), advantage, null);
        selectedAdvantageRepository.save(selectedAdvantage,selectedAdvantage.getId());
    }
    @Given("a customer who retrieved his advantage")
    public void a_customer_who_retrieved_his_advantage() throws NotAvailableAdvantageException {
        collectGift.giveGift(selectedAdvantage);
    }
    @Then("the advantage is set to Retrived status")
    public void the_advantage_is_set_to_retrived_status() {
        Assertions.assertEquals(StateSelectedAdvantage.RETRIEVED, selectedAdvantageRepository.findById(selectedAdvantage.getId()).get().getState());
    }

    @Given("a customer who retrieved his advantage one time")
    public void aCustomerWhoRetrievedHisAdvantageOneTime() throws NotAvailableAdvantageException {
        selectedAdvantage = new SelectedAdvantage(UUID.randomUUID(), StateSelectedAdvantage.AVAILABLE, new Card(), advantage, null);
        selectedAdvantageRepository.save(selectedAdvantage,selectedAdvantage.getId());
        collectGift.giveGift(selectedAdvantage);
    }

    @Then("a customer who retrieved his advantage two time and there is failure")
    public void aCustomerWhoRetrievedHisAdvantageTwoTimeAndThereIsFailure() {
        Assertions.assertThrows(NotAvailableAdvantageException.class, () -> collectGift.giveGift(selectedAdvantage));
    }
}
