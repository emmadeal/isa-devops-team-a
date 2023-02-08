package fr.univcotedazur.multifidelity.components;

import fr.univcotedazur.multifidelity.entities.Partner;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistRessourceException;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistingAccountException;
import fr.univcotedazur.multifidelity.interfaces.PartnerAccountManager;
import fr.univcotedazur.multifidelity.repositories.PartnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PartnerManagerTest {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private PartnerAccountManager partnerAccountManager;

    Partner partner;

    @BeforeEach
    public void setUpContext() {
        partnerRepository.deleteAll();
        partner = new Partner(UUID.randomUUID(), "Boulangerie", "boulang@gmail.com", "mdp");
        partnerRepository.save(partner, partner.getId());
    }

    @Test
    public void addPartnerAccountTest() throws Exception {
        Partner newPartner = new Partner(UUID.randomUUID(), "Boulangerie2", "boulang2@gmail.com", "mdp2");
        partnerAccountManager.addPartnerAccount(newPartner);
        assertEquals(2, partnerRepository.count());
    }

    @Test
    public void addPartnerAccountSameEmailTest() throws AlreadyExistingAccountException, AlreadyExistRessourceException {
        Partner copieur = new Partner(UUID.randomUUID(), "Boulangerie2", "boulang@gmail.com", "mdp2");
        try {
            partnerAccountManager.addPartnerAccount(copieur);
        }catch (AlreadyExistingAccountException e){
            assertEquals(1, partnerRepository.count());
        }
    }

}