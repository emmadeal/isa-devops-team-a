package fr.univcotedazur.multifidelity.controllers.dto_out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import static fr.univcotedazur.multifidelity.constant.Constants.Common.FLOAT_TYPE;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.LOCAL_DATE_TIME_PATTERN;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.UUID_TYPE;

@Getter
@Setter
public class PurchaseDtoOut {
    @Schema(name = "id", description = "id of the purchase ", type = UUID_TYPE, example = "1124d9e8-6266-4bcf-8035-37a02ba75c69")
    private UUID id;

    @Schema(name = "amount", description = "amount of the purchase ", type = FLOAT_TYPE, example = "17,37")
    private float amount;

    @Schema(name = "date", description = "date of the purchase ", type = LOCAL_DATE_TIME_PATTERN, example = "2021-06-02T21:33:45.249")
    private LocalDateTime date;

    @Schema(name = "societyId", description = "id of the society of the purchase", type = UUID_TYPE, example = "1124d9e8-6266-4bcf-8035-37a02ba75c69")
    private UUID societyId;

    @Schema(name = "CardId", description = "id of the card used for the purchase", type = UUID_TYPE, example = "1124d9e8-6266-4bcf-8035-37a02ba75c69")
    private UUID cardId;

    public PurchaseDtoOut(UUID id, float amount, LocalDateTime date, UUID societyId, UUID cardId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.societyId =societyId;
        this.cardId =cardId;
    }
}
