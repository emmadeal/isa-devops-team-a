package fr.univcotedazur.multifidelity.controllers.dto_out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import static fr.univcotedazur.multifidelity.constant.Constants.Common.BOOLEAN_TYPE;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.DATE_FORMAT;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.FLOAT_TYPE;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.INTEGER_TYPE;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.STRING_TYPE;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.UUID_TYPE;

@Getter
@Setter
@AllArgsConstructor
public class AdvantageDtoOut {

    @Schema (name = "id", description = "Id of the advantage ", type = STRING_TYPE, example = "1124d9e8-6266-4bcf-8035-37a02ba75c69")
    private UUID id;

    @Schema (name = "name", description = "Name of the advantage ", type = STRING_TYPE, example = "Coca")
    private String name;

    @Schema (name = "point", description = "Point of the advantage ", type = INTEGER_TYPE, example = "10")
    private int point;

    @Schema (name = "isVfpAdvantage", description = "Is the advantage a VFP advantage ", type = BOOLEAN_TYPE, example = "true")
    private boolean isVfpAdvantage;

    @Schema (name = "initialPrice", description = "Initial price of the advantage ", type = FLOAT_TYPE, example = "1.5")
    private float initialPrice;

    @Schema (name = "numberOfUse", description = "Number of use of the advantage ", type =  INTEGER_TYPE, example = "10")
    private int numberOfUse;

    @Schema (name = "societyId", description = "id of the society of the advantage ", type = UUID_TYPE, example = "1124d9e8-6266-4bcf-8035-37a02ba75c69")
    private UUID societyId;

    @Schema (name = "startValidity", description = "start of the validity of the advantage if is not a gift", type = DATE_FORMAT, example = "2021-06-02T21:33:45.249")
    private LocalDateTime startValidity;

    @Schema (name = "societyId", description = "end of the validity of the advantage if is not a gift", type = DATE_FORMAT, example = "2021-06-02T21:33:45.249")
    private LocalDateTime endValidity;

    @Schema (name = "advantageType", description = "type of the advanatage", type = STRING_TYPE, example = "GIFT")
    private String advantageType;
}
