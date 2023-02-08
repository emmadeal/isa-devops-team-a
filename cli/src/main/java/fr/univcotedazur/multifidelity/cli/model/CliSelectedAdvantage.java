package fr.univcotedazur.multifidelity.cli.model;

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
public class CliSelectedAdvantage {

    private UUID id;

    private CliStateUseAdvantage state;

    private CliCard card;

    private CliAdvantage advantage;

    private LocalDateTime lastUse ;
}
