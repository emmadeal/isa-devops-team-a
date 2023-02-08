package fr.univcotedazur.multifidelity.cli.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;


@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class CliSociety {

    private UUID id ;

    private String name;

    private CliPartner owner;

    private LocalTime openingHour = LocalTime.of(8, 0, 0, 0);

    private LocalTime closingHour = LocalTime.of(18, 0, 0, 0);

    private String address;
}
