package fr.univcotedazur.multifidelity.cli.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CliCard {

    private UUID id ;

    private int point;

    private float balance;

    private int limitPayment;

    private CliConsumer owner;
}
