package fr.univcotedazur.multifidelity.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;



@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SelectedAdvantage {

    private UUID id;

    private StateSelectedAdvantage state;

    private Card card;

    private Advantage advantage;

    private LocalDateTime lastUse ;
}
