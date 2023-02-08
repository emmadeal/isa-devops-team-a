package fr.univcotedazur.multifidelity.repositories;

import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.repositories.BasicRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SocietyRepository extends BasicRepositoryImpl<Society, UUID> {

}