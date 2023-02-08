package fr.univcotedazur.multifidelity.components;

import fr.univcotedazur.multifidelity.entities.Advantage;
import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.SelectedAdvantage;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.entities.StateSelectedAdvantage;
import fr.univcotedazur.multifidelity.exceptions.IWYPLSConnexionException;
import fr.univcotedazur.multifidelity.exceptions.NotAvailableAdvantageException;
import fr.univcotedazur.multifidelity.exceptions.NotEnoughPointsException;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.AdvantageChoices;
import fr.univcotedazur.multifidelity.interfaces.AdvantageFinder;
import fr.univcotedazur.multifidelity.interfaces.AdvantageGetter;
import fr.univcotedazur.multifidelity.interfaces.AdvantageModifier;
import fr.univcotedazur.multifidelity.interfaces.AdvantageSelectedManagement;
import fr.univcotedazur.multifidelity.interfaces.CatalogModifier;
import fr.univcotedazur.multifidelity.interfaces.CollectGift;
import fr.univcotedazur.multifidelity.interfaces.IsawWhereYouParkedLastSummer;
import fr.univcotedazur.multifidelity.interfaces.PointManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AdvantageHandler implements AdvantageModifier , AdvantageGetter, AdvantageChoices, CollectGift {

    @Autowired
    private CatalogModifier catalogModifier;

    @Autowired
    private AdvantageFinder advantageFinder;

    @Autowired
    private AdvantageSelectedManagement advantageSelectedManagement;


    @Autowired
    private PointManagement pointManagement;

    @Autowired
    private IsawWhereYouParkedLastSummer isawWhereYouParkedLastSummer;

    @Override
    public Advantage addAdvantage(Advantage advantage) {
        return null;
        // add avantage avec catalogModifier
    }

    @Override
    public void deleteAdvantage(Advantage advantage) {
        // supp avantage avec catalogModifier
    }

    @Override
    public SelectedAdvantage selectAdvantage(Advantage advantage, Consumer consumer) throws NotEnoughPointsException {
        // regarder si consumer est vfp si l'avntage l'est avec methode IsVFP
        // frecuprer la card associé
        // verfie si consumer a assez de points
        // utilise advantageSelectedManagement pour utliser
        // decremente point avec PointManagement
        return null;
    }

    @Override
    public SelectedAdvantage useAdvantage(Advantage advantage, Consumer consumer)throws IWYPLSConnexionException {
        return null;
        // verifedir que ce soit utilisable (regarder type avantage)
        // verifie si on peut l'utliser en regarder ligne correspondante
        // il faut que la ligne soit en avilable
        // verifie que la date de validité de l'offre soit encore bonne
        // midifie ligne dans SlectedAdvantage avec derniere use date et met en state USE grace a advantageSelectedManagement
    }

    @Override
    public SelectedAdvantage giveGift(SelectedAdvantage selectedAdvantage) throws NotAvailableAdvantageException {
        if(selectedAdvantage.getState()!= StateSelectedAdvantage.AVAILABLE ){
            throw new NotAvailableAdvantageException("the gift can't be received it may have already been received ");
        }
        /* a voir comment faire apres
        if(selectedAdvantage.getAdvantage().getAdvantageType()!= AdvantageType.GIFT){

        }
         */
        selectedAdvantage.setState(StateSelectedAdvantage.RETRIEVED);
        return advantageSelectedManagement.setState(selectedAdvantage,StateSelectedAdvantage.RETRIEVED);
    }

    @Override
    public Advantage getAdvantageById(UUID id) throws ResourceNotFoundException {
        return advantageFinder.findAdvantageById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("No advantage found for id: %s", id.toString())));
    }

    @Override
    public SelectedAdvantage getSelectedAdvantageById(UUID id) throws ResourceNotFoundException {
        return advantageFinder.findSelectedAdvantageById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("No selected advantage found for id: %s", id.toString())));
    }

    @Override
    public List<Advantage> getAllAdvantages() {
        return advantageFinder.getAllAdvantages();
    }

    @Override
    public List<SelectedAdvantage> getAllSelectedAdvantages() {
        return advantageFinder.getAllSelectedAdvantages();
    }

    @Override
    public List<Advantage> getAllAdvantageBySociety(Society society) {
        return  advantageFinder.getAllAdvantageBySociety(society);
    }
}
