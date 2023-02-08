package fr.univcotedazur.multifidelity.modelMapper;

import fr.univcotedazur.multifidelity.controllers.dto_out.SelectedAdvantageDtoOut;
import fr.univcotedazur.multifidelity.entities.SelectedAdvantage;
import org.springframework.stereotype.Component;


@Component
public class ModelMapperSelectedAdvantage {

    public  SelectedAdvantageDtoOut convertSelectedAdvantageToDto(SelectedAdvantage selectedAdvantage) {
        return new SelectedAdvantageDtoOut(selectedAdvantage.getId(), selectedAdvantage.getState().toString(), selectedAdvantage.getCard().getId(),selectedAdvantage.getAdvantage().getId(),selectedAdvantage.getLastUse());
    }


}
