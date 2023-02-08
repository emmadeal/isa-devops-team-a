package fr.univcotedazur.multifidelity.controllers;


import fr.univcotedazur.multifidelity.constant.RouteConstants;
import fr.univcotedazur.multifidelity.controllers.dto_in.PartnerDtoIn;
import fr.univcotedazur.multifidelity.controllers.dto_in.SocietyDtoIn;
import fr.univcotedazur.multifidelity.controllers.dto_out.AdvantageDtoOut;
import fr.univcotedazur.multifidelity.controllers.dto_out.ConsumerDtoOut;
import fr.univcotedazur.multifidelity.controllers.dto_out.ErrorDto;
import fr.univcotedazur.multifidelity.controllers.dto_out.PartnerDtoOut;
import fr.univcotedazur.multifidelity.controllers.dto_out.SocietyDtoOut;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistRessourceException;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistingAccountException;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.AdvantageModifier;
import fr.univcotedazur.multifidelity.interfaces.ConsumerGetter;
import fr.univcotedazur.multifidelity.interfaces.ConsumerInformationGiver;
import fr.univcotedazur.multifidelity.interfaces.PartnerAccountManager;
import fr.univcotedazur.multifidelity.interfaces.SocietyModifier;
import fr.univcotedazur.multifidelity.modelMapper.ModelMapperAdvantage;
import fr.univcotedazur.multifidelity.modelMapper.ModelMapperConsumer;
import fr.univcotedazur.multifidelity.modelMapper.ModelMapperPartner;
import fr.univcotedazur.multifidelity.modelMapper.ModelMapperSociety;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = RouteConstants.MayorRoutes.BASE_MAYOR_ROUTE, produces = APPLICATION_JSON_VALUE)
@Tag(name = "Mayor Controller", description = "Controller handling mayors")
public class MayorController {

    @Autowired
    private PartnerAccountManager accountModifier;

    @Autowired
    private ConsumerInformationGiver consomationInformationGiver;

    @Autowired
    private ConsumerGetter consumerGetter;

    @Autowired
    private SocietyModifier societyModifier;

    @Autowired
    private AdvantageModifier advantageModifier;


    @Autowired
    private ModelMapperSociety modelMapperSociety;

    @Autowired
    private ModelMapperPartner modelMapperPartner;

    @Autowired
    private ModelMapperConsumer modelMapperConsumer;

    @Autowired
    private ModelMapperAdvantage modelMapperAdvantage;


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorDto handleExceptions(MethodArgumentNotValidException e) {
        ErrorDto errorDTO = new ErrorDto();
        errorDTO.setError("Cannot process Mayor information because the request is not valid");
        errorDTO.setDetails(e.getMessage());
        return errorDTO;
    }


    @Operation(summary = "Create a new partner with an email and a password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creation of the partner successfully"),
            @ApiResponse(responseCode = "409", description = "this email or name is already in use by an another account "),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @PostMapping(path = "/partner", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<PartnerDtoOut> addPartnerAccount(@RequestBody @Valid PartnerDtoIn partnerDtoIn) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(modelMapperPartner.convertPartnerToDtoOut(accountModifier.addPartnerAccount(modelMapperPartner.convertDtoInToPartner(partnerDtoIn))));
        } catch (AlreadyExistingAccountException | AlreadyExistRessourceException alreadyExist ) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Operation(summary = "Create a new society")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creation of the society successfully"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
            @ApiResponse(responseCode = "409", description = "this name is already in use by an another society"),
            @ApiResponse(responseCode = "404", description = "partner not found with given id"),
   })
    @PostMapping(path = "/society", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<SocietyDtoOut> addSociety(@RequestBody @Valid SocietyDtoIn societyDtoIn) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(modelMapperSociety.convertSocietyToDtoOut(societyModifier.addSociety(modelMapperSociety.convertDtoInToSociety(societyDtoIn))));
        } catch (AlreadyExistRessourceException alreadyExistRessourceException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "get all the consumers of the platform")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful recovery of candidates"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
  })
    @GetMapping(path = "/consumers", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConsumerDtoOut>> getAllConsumer() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(consumerGetter.getAllConsumer().stream().map(modelMapperConsumer::convertConsumerToDto)
                .collect(Collectors.toList()));
    }


    @Operation(summary = "the mayor add an advantage to the catalog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Advantage added successfully"),
            @ApiResponse(responseCode = "404", description = "The society of the advantage was not found"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @PostMapping(path = "/advantage", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<AdvantageDtoOut> addAdvantage(@RequestBody @Valid AdvantageDtoOut advantageDTO)  {
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperAdvantage.convertAdvantageToDto(advantageModifier.addAdvantage(modelMapperAdvantage.convertDtoToAdvantage(advantageDTO))));

        }catch (ResourceNotFoundException resourceNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
  }



}
