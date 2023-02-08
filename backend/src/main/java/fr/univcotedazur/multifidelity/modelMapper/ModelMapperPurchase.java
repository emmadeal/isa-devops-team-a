package fr.univcotedazur.multifidelity.modelMapper;

import fr.univcotedazur.multifidelity.controllers.dto_out.PurchaseDtoOut;
import fr.univcotedazur.multifidelity.entities.Card;
import fr.univcotedazur.multifidelity.entities.Purchase;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.CardGetter;
import fr.univcotedazur.multifidelity.interfaces.SocietyGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ModelMapperPurchase {

    @Autowired
    private  SocietyGetter societyGetter;

    @Autowired
    private  CardGetter cardGetter;


    public  Purchase convertDtoToPurchase(PurchaseDtoOut purchaseDTO) throws ResourceNotFoundException { // In more complex cases, we could use ModelMapper
        Society shop = societyGetter.getSocietyById(purchaseDTO.getSocietyId());
        Card card = cardGetter.getCardById(purchaseDTO.getSocietyId());
        return new Purchase(purchaseDTO.getId(), purchaseDTO.getAmount(), purchaseDTO.getDate(),shop,card);
    }

    public  PurchaseDtoOut convertPurchaseToDto(Purchase purchase) { // In more complex cases, we could use ModelMapper
        return new PurchaseDtoOut(purchase.getId(), purchase.getAmount(), purchase.getDate(),purchase.getSociety().getId(),purchase.getCard().getId());
    }
}