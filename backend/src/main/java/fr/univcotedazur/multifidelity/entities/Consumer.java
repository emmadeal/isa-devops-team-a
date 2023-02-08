package fr.univcotedazur.multifidelity.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {

    private UUID id ;

    private String email;

    private String password;

    private String creditCardNumber;

    private String licencePlate;

    private boolean isVfp;

    private List<Society> favSocieties = new ArrayList<>();


    public Consumer(String email, String password, String creditCardNumber, String licencePlate) {
        this.email = email;
        this.password = password;
        this.creditCardNumber = creditCardNumber;
        this.licencePlate = licencePlate;
    }
}
