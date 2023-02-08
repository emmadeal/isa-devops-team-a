package fr.univcotedazur.multifidelity.components;

import fr.univcotedazur.multifidelity.entities.Partner;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistRessourceException;
import fr.univcotedazur.multifidelity.interfaces.SocietiesInformationGetter;
import fr.univcotedazur.multifidelity.interfaces.SocietyModifier;
import fr.univcotedazur.multifidelity.repositories.SocietyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SocietyManagerTest {

    @Autowired
    SocietyRepository societyRepository;

    @Autowired
    private SocietiesInformationGetter societyInformationGetter;

    @Autowired
    private SocietyModifier societyModifier;

    Society society;

    @BeforeEach
    public void setUpContext() {
        societyRepository.deleteAll();
        society = new Society();
        society.setName("Boulangerie");
        societyRepository.save(society, society.getId());
    }

    @Test
    public void getAllSocietiesTest() throws Exception {
        assertEquals(1, societyInformationGetter.getAllSocieties().size());
    }

    @Test
    public void getSocietyByNameTest() throws Exception {
        assertEquals(society, societyInformationGetter.getSocietyByName("Boulangerie"));
    }

    @Test
    public void getSocietyByIdTest() throws Exception {
        assertEquals(society, societyInformationGetter.getSocietyById(society.getId()));
    }

    @Test
    public void addSocietyTest() throws AlreadyExistRessourceException {
        Society society2 = new Society(UUID.randomUUID(), "Boulangerie2", new Partner(), LocalTime.of(8,0), LocalTime.of(19,0), "73 rue blabla");
        societyModifier.addSociety(society2);
        assertEquals(2, societyInformationGetter.getAllSocieties().size());
    }
}