package fr.univcotedazur.multifidelity.repositories;

import fr.univcotedazur.multifidelity.entities.Advantage;
import fr.univcotedazur.repositories.BasicRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AdvantageRepository extends BasicRepositoryImpl<Advantage, UUID> {

}
