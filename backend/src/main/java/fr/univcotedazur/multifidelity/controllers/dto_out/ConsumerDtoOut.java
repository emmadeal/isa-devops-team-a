package fr.univcotedazur.multifidelity.controllers.dto_out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import static fr.univcotedazur.multifidelity.constant.Constants.Common.INTEGER_TYPE;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.STRING_TYPE;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.UUID_TYPE;

@Getter
@Setter
public class ConsumerDtoOut {

    @Schema(name = "id", description = "Id of the consumer ", type = UUID_TYPE, example = "1124d9e8-6266-4bcf-8035-37a02ba75c69")
    private UUID id ;

    @Schema(name = "email", description = "email of the consumer ", type = STRING_TYPE, example = "clairemarini06390@gmail.com")
    private String email;

    @Schema(name = "password", description = "password of the consumer ", type = STRING_TYPE, example = "claire06")
    private String password;

    @Schema(name = "creditCardNumber", description = "credit card number of the consumer ", type = STRING_TYPE, example = "1737475968")
    private String creditCardNumber;

    @Schema(name = "licencePlate", description = "licence plate of the consumer ", type = STRING_TYPE, example = "12-AZE-62")
    private String licencePlate;


    @Schema(name = "favSocietyIds", description = "list of the ids of the fav societies of the consumer", type = INTEGER_TYPE, example = "[12,3]")
    private List<UUID> favSocietyIds;

    public ConsumerDtoOut(UUID id, String email, String password, String creditCardNumber, String licencePlate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.creditCardNumber = creditCardNumber;
        this.licencePlate = licencePlate;
    }
}