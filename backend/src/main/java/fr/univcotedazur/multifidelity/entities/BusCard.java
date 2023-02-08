package fr.univcotedazur.multifidelity.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BusCard {

    private UUID id;

    private String name;

    private int point;

    private float initialPrice;

    private int numberOfUse;

    private Society shop;

    private LocalDateTime startValidity;

    private LocalDateTime endValidity;

    private boolean ilimited;

    private int numberOfTrip;
}
