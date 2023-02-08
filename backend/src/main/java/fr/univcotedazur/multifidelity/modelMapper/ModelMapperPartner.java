package fr.univcotedazur.multifidelity.modelMapper;

import fr.univcotedazur.multifidelity.controllers.dto_in.PartnerDtoIn;
import fr.univcotedazur.multifidelity.controllers.dto_out.PartnerDtoOut;
import fr.univcotedazur.multifidelity.entities.Partner;
import org.springframework.stereotype.Component;


@Component
public class ModelMapperPartner {

    public  PartnerDtoOut convertPartnerToDtoOut(Partner partner) {
        return new PartnerDtoOut(partner.getId(), partner.getName(), partner.getEmail(), partner.getPassword());
    }


    public  Partner convertDtoInToPartner(PartnerDtoIn partnerDtoIn) {
        return new Partner(partnerDtoIn.getName(), partnerDtoIn.getEmail(), partnerDtoIn.getPassword());
    }

}
