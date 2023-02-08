package fr.univcotedazur.multifidelity.modelMapper;

import fr.univcotedazur.multifidelity.controllers.dto_out.CardDtoOut;
import fr.univcotedazur.multifidelity.entities.Card;
import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.ConsumerFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ModelMapperCard {

    @Autowired
    private  ConsumerFinder consumerFinder;

    public  Card convertDtoToCard(CardDtoOut cardDTO) throws ResourceNotFoundException { // In more complex cases, we could use ModelMapper
        Consumer consumer = consumerFinder.findConsumerById(cardDTO.getOwnerId()).orElseThrow(() -> new ResourceNotFoundException("consumer" + "id" + cardDTO.getOwnerId().toString()));
        return new Card(cardDTO.getId(), cardDTO.getPoint(), cardDTO.getBalance(), cardDTO.getLimitPayment(),consumer);
    }

    public  CardDtoOut convertCardToDto(Card card) { // In more complex cases, we could use ModelMapper
        return new CardDtoOut(card.getId(), card.getPoint(), card.getBalance(),card.getLimitPayment(), card.getOwner().getId());
    }
}
