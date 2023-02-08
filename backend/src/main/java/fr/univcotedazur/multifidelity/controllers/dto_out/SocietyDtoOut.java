package fr.univcotedazur.multifidelity.controllers.dto_out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

import static fr.univcotedazur.multifidelity.constant.Constants.Common.DATE_FORMAT;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.STRING_TYPE;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.UUID_TYPE;

@Getter
@Setter
@AllArgsConstructor
public class SocietyDtoOut {

    @Schema(name = "id", description = "Id of the society ", type = UUID_TYPE, example = "1124d9e8-6266-4bcf-8035-37a02ba75c69")
    private UUID id ;

    @Schema(name = "name", description = "name of the society ", type = STRING_TYPE, example = "Pizza fiesta")
    private String name;

    @Schema(name = "ownerId", description = "id of the owner of the society ", type = UUID_TYPE, example = "1124d9e8-6266-4bcf-8035-37a02ba75c69")
    private UUID ownerId;

    @Schema(name = "openingHour", description = "Date and Time of the opening of the society", type = DATE_FORMAT, example = "10:15:30")
    private LocalTime openingHour =LocalTime.of(8, 0, 0, 0);

    @Schema(name = "closingHour", description = "Date and Time of the closing of the society", type = DATE_FORMAT, example = "18:15:30")
    private LocalTime closingHour = LocalTime.of(18, 0, 0, 0);

    @Schema(name = "address", description = "address of the society ", type = STRING_TYPE, example = "12 rue Matis")
    private String address;
}
