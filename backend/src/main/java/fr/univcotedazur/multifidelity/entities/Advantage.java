package fr.univcotedazur.multifidelity.entities;

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
public class Advantage {

    private UUID id;

    private String name;

    private int point;

    private boolean isVfpAdvantage;

    private float initialPrice;

    private int numberOfUse;

    private Society society;

    private LocalDateTime startValidity;

    private LocalDateTime endValidity;

    private AdvantageType advantageType;
}
