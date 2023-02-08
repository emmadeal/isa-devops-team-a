package fr.univcotedazur.multifidelity.cucumber.Society;

import fr.univcotedazur.multifidelity.components.SocietyManager;
import fr.univcotedazur.multifidelity.entities.Partner;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.repositories.SocietyRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

@SpringBootTest
public class UpdateInfos {

    @Autowired
    private SocietyRepository societyRepository;

    @Autowired
    private SocietyManager societyManager;

    private Society oldSociety;

    private Society newSociety;

    private Partner partner;

    @Before
    public void setUp() {
        societyRepository.deleteAll();
        partner = new Partner();
    }

    @Given("a society called {string} opening at {int}:{int} and closing at {int}:{int}, located in {string} is created")
    public void aSocietyCalledOpeningAtAndClosingAtLocatedInWithPhoneNumberIsCreated(String arg0, int arg1, int arg2, int arg3, int arg4, String arg5) {
        oldSociety = Society.builder().name(arg0).openingHour(LocalTime.of(arg1, arg2)).closingHour(LocalTime.of(arg3, arg4)).address(arg5).owner(partner).build();
        societyRepository.save(oldSociety, oldSociety.getId());
        newSociety = new Society(oldSociety.getName(),oldSociety.getOwner(),oldSociety.getOpeningHour(),oldSociety.getClosingHour(),oldSociety.getAddress());
    }



    @When("the partner change the location of the society to {string}")
    public void thePartnerChangeTheLocationOfTheSocietyTo(String arg0) throws ResourceNotFoundException {
        newSociety.setAddress(arg0);
        societyManager.updateSocietyInformation(oldSociety,newSociety);
    }

    @Then("the society {string} is located in {string}")
    public void theSocietyIsLocatedIn(String arg0, String arg1) {
        assert societyRepository.findById(oldSociety.getId()).get().getAddress().equals(arg1);
    }

    @When("the partner change the opening time of the society to {int}:{int}")
    public void thePartnerChangeTheOpeningTimeOfTheSocietyTo(int arg0, int arg1) throws ResourceNotFoundException {
        newSociety.setOpeningHour(LocalTime.of(arg0, arg1));
        societyManager.updateSocietyInformation(oldSociety,newSociety);
    }

    @Then("the society {string} is open at {int}:{int}")
    public void theSocietyIsOpenAt(String arg0, int arg1, int arg2) {
        assert societyRepository.findById(oldSociety.getId()).get().getOpeningHour().equals(LocalTime.of(arg1, arg2));
    }

    @And("a notification is sent")
    public void aNotificationIsSent() {
        // how to check??
    }

    @When("the partner change the closing time of the society to {int}:{int}")
    public void thePartnerChangeTheClosingTimeOfTheSocietyTo(int arg0, int arg1) throws ResourceNotFoundException {
        newSociety.setClosingHour(LocalTime.of(arg0, arg1));
        societyManager.updateSocietyInformation(oldSociety,newSociety);
    }

    @Then("the society {string} is closed at {int}:{int}")
    public void theSocietyIsClosedAt(String arg0, int arg1, int arg2) {
        assert societyRepository.findById(oldSociety.getId()).get().getClosingHour().equals(LocalTime.of(arg1, arg2));
    }
}
