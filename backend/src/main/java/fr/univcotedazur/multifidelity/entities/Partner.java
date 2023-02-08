package fr.univcotedazur.multifidelity.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Partner {

    private UUID id;

    private String name;

    private String email;

    private String password;

    public Partner(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
