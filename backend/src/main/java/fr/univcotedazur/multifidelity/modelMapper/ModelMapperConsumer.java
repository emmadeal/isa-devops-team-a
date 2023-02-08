package fr.univcotedazur.multifidelity.modelMapper;


import fr.univcotedazur.multifidelity.controllers.dto_in.ConsumerDtoIn;
import fr.univcotedazur.multifidelity.controllers.dto_out.ConsumerDtoOut;
import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.Society;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class ModelMapperConsumer {

    public  Consumer convertDtoInToConsumer(ConsumerDtoIn consumerDtoIn) { // In more complex cases, we could use ModelMapper
        return new Consumer(consumerDtoIn.getEmail(), consumerDtoIn.getPassword(),consumerDtoIn.getCreditCardNumber(),consumerDtoIn.getLicencePlate());

    }

    public  ConsumerDtoOut convertConsumerToDto(Consumer consumer) { // In more complex cases, we could use ModelMapper
        ConsumerDtoOut consumerDTO = new ConsumerDtoOut(consumer.getId(), consumer.getEmail(), consumer.getPassword(),consumer.getCreditCardNumber(),consumer.getLicencePlate());
        consumerDTO.setFavSocietyIds(consumer.getFavSocieties().stream().map(Society::getId)
                .collect(Collectors.toList()));
        return consumerDTO;
    }
}
