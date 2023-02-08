package fr.univcotedazur.multifidelity.repositories;

import fr.univcotedazur.multifidelity.entities.Purchase;
import fr.univcotedazur.repositories.BasicRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PurchaseRepository extends BasicRepositoryImpl<Purchase, UUID> {

}