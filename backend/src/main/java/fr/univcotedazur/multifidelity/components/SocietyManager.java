package fr.univcotedazur.multifidelity.components;

import fr.univcotedazur.multifidelity.entities.Advantage;
import fr.univcotedazur.multifidelity.entities.Partner;
import fr.univcotedazur.multifidelity.entities.ScheduleType;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistRessourceException;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.CatalogModifier;
import fr.univcotedazur.multifidelity.interfaces.NotificationSender;
import fr.univcotedazur.multifidelity.interfaces.SocietiesInformationGetter;
import fr.univcotedazur.multifidelity.interfaces.SocietyGetter;
import fr.univcotedazur.multifidelity.interfaces.SocietyModifier;
import fr.univcotedazur.multifidelity.repositories.SocietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class SocietyManager implements SocietyGetter, SocietyModifier, SocietiesInformationGetter {

    @Autowired
    private SocietyRepository societyRepository;

    @Autowired
    private CatalogModifier catalogModifier;

    @Autowired
    private NotificationSender notificationSender;

    public Society getSocietyById(UUID id) throws ResourceNotFoundException {
        return findSocietyById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("No society found for id: %s", id.toString())));
    }


    private Optional<Society> findSocietyById(UUID id) {
        return societyRepository.findById(id);
    }


    @Override
    public Society updateSocietyInformation(Society oldSociety,Society newSociety)  {
        if (newSociety.getOpeningHour().compareTo(oldSociety.getOpeningHour()) != 0)
            notificationSender.notifyConsumerNewSchedule(newSociety.getOpeningHour(), newSociety, ScheduleType.OPENING);
        if (newSociety.getClosingHour().compareTo(oldSociety.getClosingHour()) != 0)
            notificationSender.notifyConsumerNewSchedule(newSociety.getClosingHour(), newSociety, ScheduleType.CLOSING);
        oldSociety.setAddress(newSociety.getAddress());
        oldSociety.setOpeningHour(newSociety.getOpeningHour());
        oldSociety.setClosingHour(newSociety.getClosingHour());
        return societyRepository.save(oldSociety, oldSociety.getId());
    }

    @Override
    public Advantage addAdvantage(Advantage advantage) {
        return null;
        //catalog modifier usage
    }

    @Override
    public void deleteAdvantage(Advantage advantage) {
        // appeler advantageModifier
    }

    @Override
    public Society addSociety(Society newSociety) throws AlreadyExistRessourceException {
        if (findSocietyByNameByPartner(newSociety.getName(),newSociety.getOwner()).isPresent()) {
            throw new AlreadyExistRessourceException(String.format("Society already exists with this name : %s",newSociety.getName()));
        }else {
            newSociety.setId(UUID.randomUUID());
            return societyRepository.save(newSociety, newSociety.getId());
        }
    }

    private Optional<Society> findSocietyByNameByPartner(String name, Partner partner) {
        return StreamSupport.stream(societyRepository.findAll().spliterator(), false)
                .filter(society -> society.getOwner()==partner && name.equals(society.getName())).findAny();
    }

    @Override
    public List<Society> getAllSocieties() {
        return StreamSupport.stream(societyRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Society getSocietyByName(String name) throws ResourceNotFoundException {
        return StreamSupport.stream(societyRepository.findAll().spliterator(), false).filter(society -> society.getName() == name).findFirst().orElseThrow(() -> new ResourceNotFoundException(String.format("No society found for name: %s", name)));
    }
}
