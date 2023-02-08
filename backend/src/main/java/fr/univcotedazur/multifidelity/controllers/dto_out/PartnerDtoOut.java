package fr.univcotedazur.multifidelity.controllers.dto_out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static fr.univcotedazur.multifidelity.constant.Constants.Common.STRING_TYPE;
import static fr.univcotedazur.multifidelity.constant.Constants.Common.UUID_TYPE;

@Getter
@Setter
@AllArgsConstructor
public class PartnerDtoOut {
    @Schema(name = "id", description = "Id of the consumer ", type = UUID_TYPE, example = "1124d9e8-6266-4bcf-8035-37a02ba75c69")
    private UUID id;

    @Schema(name = "name", description = "name of the consumer ", type = STRING_TYPE, example = "claire")
    private String name;

    @Schema(name = "email", description = "email of the consumer ", type = STRING_TYPE, example = "clairemarini06390@gmail.com")
    private String email;

    @Schema(name = "password", description = "password of the consumer ", type = STRING_TYPE, example = "claire06")
    private String password;
}
