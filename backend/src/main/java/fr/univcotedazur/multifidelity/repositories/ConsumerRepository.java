package fr.univcotedazur.multifidelity.repositories;

import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.repositories.BasicRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ConsumerRepository extends BasicRepositoryImpl<Consumer, UUID> {

}
