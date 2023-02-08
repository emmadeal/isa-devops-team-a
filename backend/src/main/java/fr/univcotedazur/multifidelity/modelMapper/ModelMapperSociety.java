package fr.univcotedazur.multifidelity.modelMapper;

import fr.univcotedazur.multifidelity.controllers.dto_in.SocietyDtoIn;
import fr.univcotedazur.multifidelity.controllers.dto_out.SocietyDtoOut;
import fr.univcotedazur.multifidelity.entities.Partner;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.PartnerGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperSociety {

    @Autowired
    private  PartnerGetter partnerGetter;

    public Society convertDtoInToSociety(SocietyDtoIn societyDtoIn) throws ResourceNotFoundException {
        Partner partner = partnerGetter.getPartnerById(societyDtoIn.getOwnerId());
        return new Society(societyDtoIn.getName(), partner, societyDtoIn.getOpeningHour(), societyDtoIn.getClosingHour(), societyDtoIn.getAddress());
    }

    public SocietyDtoOut convertSocietyToDtoOut(Society society) {
        return new SocietyDtoOut(society.getId(), society.getName(), society.getOwner().getId(), society.getOpeningHour(), society.getClosingHour(), society.getAddress());
    }
}
