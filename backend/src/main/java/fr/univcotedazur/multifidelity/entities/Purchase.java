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
public class Purchase {

    private UUID id;

    private float amount;

    private LocalDateTime date;

    private Society society;

    private Card card;
}
