package fr.univcotedazur.multifidelity.components;

import fr.univcotedazur.multifidelity.entities.Partner;
import fr.univcotedazur.multifidelity.exceptions.AccountNotFoundException;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistRessourceException;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistingAccountException;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.PartnerAccountManager;
import fr.univcotedazur.multifidelity.interfaces.PartnerGetter;
import fr.univcotedazur.multifidelity.repositories.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Component
public class PartnerManager implements PartnerAccountManager, PartnerGetter {


    @Autowired
    private PartnerRepository partnerRepository;


    private Optional<Partner> findByIdPartner(UUID id) {
        return partnerRepository.findById(id);
    }

    public Partner getPartnerById(UUID id) throws ResourceNotFoundException {
        return findByIdPartner(id).orElseThrow(() -> new ResourceNotFoundException(String.format("No partner found for id: %s", id.toString())));
    }

    @Override
    public Partner login(String email, String password) throws AccountNotFoundException {
        return StreamSupport.stream(partnerRepository.findAll().spliterator(), false)
                .filter(partner -> partner.getEmail().equals(email) && partner.getPassword().equals(password))
                .findFirst()
                .orElseThrow(()-> new AccountNotFoundException("No partner account was found"));
    }


    @Override
    public Partner addPartnerAccount(Partner newPartner) throws AlreadyExistingAccountException, AlreadyExistRessourceException {
        boolean alreadyExisting = StreamSupport.stream(partnerRepository.findAll().spliterator(), false)
                .anyMatch(p -> p.getEmail().equals(newPartner.getEmail()));
        if (alreadyExisting) {
            throw new AlreadyExistingAccountException(String.format("Partner with this email address already exists : %s ",newPartner.getEmail()));
        }
        if(findPartnerByName(newPartner.getName()).isPresent()){
            throw new AlreadyExistRessourceException(String.format("Partner already exists with this name : %s",newPartner.getName()));
        }
        newPartner.setId(UUID.randomUUID());
        return partnerRepository.save(newPartner, newPartner.getId());
    }


    private Optional<Partner> findPartnerByName(String name) {
        return StreamSupport.stream(partnerRepository.findAll().spliterator(), false)
                .filter(partner -> name.equals(partner.getName())).findAny();
    }


}
