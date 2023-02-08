package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.Card;
import fr.univcotedazur.multifidelity.exceptions.NegativeQuantityException;

public interface PointManagement {

    int updatePoints(int numberOfPoints, Card card) throws NegativeQuantityException;

}
