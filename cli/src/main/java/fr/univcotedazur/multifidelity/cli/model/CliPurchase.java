package fr.univcotedazur.multifidelity.cli.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CliPurchase {

    private UUID id;

    private float amount;

    private LocalDateTime date;

    private CliSociety shop;

    private CliCard card;
}
