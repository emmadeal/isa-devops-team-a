package fr.univcotedazur.multifidelity.components;

import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.interfaces.FrequencyPurchaseAnalyst;
import fr.univcotedazur.multifidelity.interfaces.StatisticGiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticService implements StatisticGiver {


    @Autowired
    private FrequencyPurchaseAnalyst frequencyPurchaseAnalyst;


    @Override
    public float getProfitBySocietyId(Society shop) {
        return 0;
        //calcul benefice pour magasin (vente -gift)
    }

    @Override
    public float getProfit() {
        return 0;
        //calcul benefice pour ville (vente -gift)
    }


    // habitufde de conso
}
