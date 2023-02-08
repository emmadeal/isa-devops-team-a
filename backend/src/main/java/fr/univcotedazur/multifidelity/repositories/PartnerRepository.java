package fr.univcotedazur.multifidelity.repositories;

import fr.univcotedazur.multifidelity.entities.Partner;
import fr.univcotedazur.repositories.BasicRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PartnerRepository extends BasicRepositoryImpl<Partner, UUID> {

}
