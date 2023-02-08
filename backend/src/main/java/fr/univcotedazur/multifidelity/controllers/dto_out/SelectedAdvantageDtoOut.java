package fr.univcotedazur.multifidelity.controllers.dto_out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import static fr.univcotedazur.multifidelity.constant.Constants.Common.DATE_FORMAT;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.STRING_TYPE;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.UUID_TYPE;


@Getter
@Setter
@AllArgsConstructor
public class SelectedAdvantageDtoOut {

    @Schema(name = "id", description = "Id of the selectedAdvantage ", type = UUID_TYPE, example = "1124d9e8-6266-4bcf-8035-37a02ba75c69")
    private UUID id;

    @Schema(name = "state", description = "state of the selectedAdvantage ", type = STRING_TYPE, example = "RETRY")
    private String state;

    @Schema(name = "cardId", description = "Id of the card of the selectedAdvantage ", type = UUID_TYPE, example = "1124d9e8-6266-4bcf-8035-37a02ba75c69")
    private UUID cardId;

    @Schema(name = "advantageId", description = "Id of the avantage of the selectedAdvantage ", type = UUID_TYPE, example = "1124d9e8-6266-4bcf-8035-37a02ba75c69")
    private UUID advantageId;

    @Schema(name = "lastUse", description = "date the last use of the selectedAdvantage ", type = DATE_FORMAT, example = "2022-09-30 07:57:22")
    private LocalDateTime lastUse;
}
