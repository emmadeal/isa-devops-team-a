package fr.univcotedazur.multifidelity.controllers.dto_out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static fr.univcotedazur.multifidelity.constant.Constants.Common.FLOAT_TYPE;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.INTEGER_TYPE;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.UUID_TYPE;


@Getter
@Setter
public class CardDtoOut {
    @Schema(name = "id", description = "id of the card ", type = UUID_TYPE, example = "1124d9e8-6266-4bcf-8035-37a02ba75c69")
    private UUID id;

    @Schema(name = "point", description = "points on the card ", type = INTEGER_TYPE, example = "17")
    private int point;

    @Schema(name = "balance", description = "balance on the card ", type = FLOAT_TYPE, example = "17,37")
    private float balance;

    @Schema(name = "limitPayment", description = "limit of payment ", type = INTEGER_TYPE, example = "17")
    private int limitPayment;

    @Schema(name = "ownerId", description = "id of the card's owner", type = UUID_TYPE, example = "1124d9e8-6266-4bcf-8035-37a02ba75c69")
    private UUID ownerId;

    public CardDtoOut(UUID id, int point, float balance, int limitPayment, UUID ownerId) {
        this.id = id;
        this.point = point;
        this.balance = balance;
        this.limitPayment = limitPayment;
        this.ownerId = ownerId;
    }
}
