package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Society;

public interface StatisticGiver {

    float getProfitBySocietyId(Society society) ;

    float getProfit();

    // calcul de si c'est rentable
    // calcul benefice/perte par societe et en tout pour la ville


    //habitude de consommation
}
