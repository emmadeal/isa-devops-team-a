package fr.univcotedazur.multifidelity.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Builder
@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Society {

    private UUID id ;

    private String name;

    private Partner owner;

    private LocalTime openingHour = LocalTime.of(8, 0, 0, 0);

    private LocalTime closingHour = LocalTime.of(18, 0, 0, 0);

    private String address;

    public Society(String name, Partner owner, LocalTime openingHour, LocalTime closingHour, String address) {
        this.name = name;
        this.owner = owner;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.address = address;
    }
}
