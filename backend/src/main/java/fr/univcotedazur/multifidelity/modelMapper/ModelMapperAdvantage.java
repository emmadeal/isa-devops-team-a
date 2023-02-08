package fr.univcotedazur.multifidelity.modelMapper;



import fr.univcotedazur.multifidelity.controllers.dto_out.AdvantageDtoOut;
import fr.univcotedazur.multifidelity.entities.Advantage;
import fr.univcotedazur.multifidelity.entities.AdvantageType;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.SocietyGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperAdvantage {

    @Autowired
    private  SocietyGetter societyGetter;

    public  AdvantageDtoOut convertAdvantageToDto(Advantage advantage) {
        return new AdvantageDtoOut(advantage.getId(), advantage.getName(), advantage.getPoint(), advantage.isVfpAdvantage(), advantage.getInitialPrice(), advantage.getNumberOfUse(), advantage.getSociety().getId(),advantage.getStartValidity(),advantage.getEndValidity(),advantage.getAdvantageType().name());
    }

    public  Advantage convertDtoToAdvantage(AdvantageDtoOut advantageDTO) throws ResourceNotFoundException {
        Society shop = societyGetter.getSocietyById(advantageDTO.getSocietyId());
        AdvantageType advantageType = AdvantageType.valueOf(advantageDTO.getAdvantageType());
        return new Advantage(advantageDTO.getId(), advantageDTO.getName(), advantageDTO.getPoint(), advantageDTO.isVfpAdvantage(), advantageDTO.getInitialPrice(), advantageDTO.getNumberOfUse(), shop,advantageDTO.getStartValidity(),advantageDTO.getEndValidity(),advantageType);
    }
}
