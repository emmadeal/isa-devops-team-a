package fr.univcotedazur.multifidelity.controllers;

import fr.univcotedazur.multifidelity.constant.RouteConstants;
import fr.univcotedazur.multifidelity.controllers.dto_in.PartnerDtoIn;
import fr.univcotedazur.multifidelity.controllers.dto_in.SocietyDtoIn;
import fr.univcotedazur.multifidelity.controllers.dto_out.AdvantageDtoOut;
import fr.univcotedazur.multifidelity.controllers.dto_out.ErrorDto;
import fr.univcotedazur.multifidelity.controllers.dto_out.PartnerDtoOut;
import fr.univcotedazur.multifidelity.controllers.dto_out.SocietyDtoOut;
import fr.univcotedazur.multifidelity.entities.Advantage;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.AccountNotFoundException;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.AdvantageGetter;
import fr.univcotedazur.multifidelity.interfaces.AdvantageModifier;
import fr.univcotedazur.multifidelity.interfaces.PartnerAccountManager;
import fr.univcotedazur.multifidelity.interfaces.SocietyGetter;
import fr.univcotedazur.multifidelity.interfaces.SocietyModifier;
import fr.univcotedazur.multifidelity.modelMapper.ModelMapperAdvantage;
import fr.univcotedazur.multifidelity.modelMapper.ModelMapperPartner;
import fr.univcotedazur.multifidelity.modelMapper.ModelMapperSociety;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@Slf4j
@RequestMapping(path = RouteConstants.PartnerRoutes.BASE_PARTNER_ROUTE, produces = APPLICATION_JSON_VALUE)
@Tag(name = "Partner Controller", description = "Controller handling partner")
public class PartnerController {


    @Autowired
    private PartnerAccountManager partnerAccountManager;

    @Autowired
    private AdvantageModifier advantageModifier;

    @Autowired
    private SocietyModifier societyModifier;

    @Autowired
    private AdvantageGetter advantageGetter;

    @Autowired
    private SocietyGetter societyGetter;

    @Autowired
    private ModelMapperSociety modelMapperSociety;

    @Autowired
    private ModelMapperPartner modelMapperPartner;

    @Autowired
    private ModelMapperAdvantage modelMapperAdvantage;



    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorDto handleExceptions(MethodArgumentNotValidException e) {
        ErrorDto errorDTO = new ErrorDto();
        errorDTO.setError("Cannot process Partner information because the request is not valid");
        errorDTO.setDetails(e.getMessage());
        return errorDTO;
    }


    @Operation(summary = "LogIn of an existing partner with his email and his password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "LogIn of the partner successfully"),
            @ApiResponse(responseCode = "404", description = "A partner with this email and this password was not found"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @PostMapping(path = "/login", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<PartnerDtoOut> login(@RequestBody PartnerDtoIn partnerDtoIn) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperPartner.convertPartnerToDtoOut(partnerAccountManager.login(partnerDtoIn.getEmail(), partnerDtoIn.getPassword())));
        } catch (AccountNotFoundException accountNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "the partner add an advantage to the catalog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Advantage added successfully"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @PostMapping(path = "/advantage", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<AdvantageDtoOut> addAdvantage(@RequestBody @Valid AdvantageDtoOut advantageDTO) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapperAdvantage.convertAdvantageToDto(advantageModifier.addAdvantage(modelMapperAdvantage.convertDtoToAdvantage(advantageDTO))));
    }

    @Operation(summary = "the partner delete an advantage to the catalog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Advantage deleted successfully"),
            @ApiResponse(responseCode = "404", description = "The advantage with given id was not found"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @DeleteMapping(path = "/advantage/{advantageId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<AdvantageDtoOut> deleteAdvantage(@PathVariable UUID advantageId) throws ResourceNotFoundException {
        try{
            Advantage advantage = advantageGetter.getAdvantageById(advantageId);
            advantageModifier.deleteAdvantage(advantage);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperAdvantage.convertAdvantageToDto(advantage));
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "the partner update his society's informations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "society updated successfully"),
            @ApiResponse(responseCode = "404", description = "The society with given id was not found"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @PutMapping(path = "/society/{societyId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<SocietyDtoOut> updateSocietyInformation(@PathVariable UUID societyId,@RequestBody @Valid SocietyDtoIn societyDtoIn) {
        try {
            Society oldSociety = societyGetter.getSocietyById(societyId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperSociety.convertSocietyToDtoOut(societyModifier.updateSocietyInformation(oldSociety,modelMapperSociety.convertDtoInToSociety(societyDtoIn))));
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

