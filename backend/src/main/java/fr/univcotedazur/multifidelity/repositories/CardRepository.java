package fr.univcotedazur.multifidelity.repositories;

import fr.univcotedazur.multifidelity.entities.Card;
import fr.univcotedazur.repositories.BasicRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CardRepository extends BasicRepositoryImpl<Card, UUID> {

}
