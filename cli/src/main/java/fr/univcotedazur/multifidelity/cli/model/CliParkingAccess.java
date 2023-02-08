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
public class CliParkingAccess {

    private UUID id;

    private String name;

    private int point;

    private float initialPrice;

    private int numberOfUse;

    private CliSociety shop;

    private LocalDateTime startValidity;

    private LocalDateTime endValidity;

    private int durationMinute;

}
