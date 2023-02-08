package fr.univcotedazur.multifidelity.controllers;


import fr.univcotedazur.multifidelity.constant.RouteConstants;
import fr.univcotedazur.multifidelity.controllers.dto_out.AdvantageDtoOut;
import fr.univcotedazur.multifidelity.controllers.dto_out.ErrorDto;
import fr.univcotedazur.multifidelity.controllers.dto_out.SocietyDtoOut;
import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.AdvantageGetter;
import fr.univcotedazur.multifidelity.interfaces.ConsumerGetter;
import fr.univcotedazur.multifidelity.interfaces.ConsumerInformationGiver;
import fr.univcotedazur.multifidelity.interfaces.SocietiesInformationGetter;
import fr.univcotedazur.multifidelity.interfaces.SocietyGetter;
import fr.univcotedazur.multifidelity.modelMapper.ModelMapperAdvantage;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@RequestMapping(path = RouteConstants.ShowCasesRoutes.BASE_SHOW_CASES_ROUTE, produces = APPLICATION_JSON_VALUE)
@Tag(name = "ShowCase Controller", description = "Controller handling showcase")
public class ShowCaseController {

    @Autowired
    private SocietiesInformationGetter societiesInformationGetter;

    @Autowired
    private ConsumerGetter consumerGetter;

    @Autowired
    private ConsumerInformationGiver consumerInformationGiver;


    @Autowired
    private AdvantageGetter advantageGetter;

    @Autowired
    private SocietyGetter societyGetter;

    @Autowired
    private ModelMapperSociety modelMapperSociety;

    @Autowired
    private ModelMapperAdvantage modelMapperAdvantage;

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    // The 422 (Unprocessable Entity) status code means the server understands the content type of the request entity
    // (hence a 415(Unsupported Media Type) status code is inappropriate), and the syntax of the request entity is
    // correct (thus a 400 (Bad Request) status code is inappropriate) but was unable to process the contained
    // instructions.
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorDto handleExceptions(MethodArgumentNotValidException e) {
        ErrorDto errorDTO = new ErrorDto();
        errorDTO.setError("Cannot process Show cases societies informations");
        errorDTO.setDetails(e.getMessage());
        return errorDTO;
    }

    @Operation(summary = "Get list of all societies of the platform")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "societies successfully returned "),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @GetMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SocietyDtoOut>> getAllSocieties() {
        return ResponseEntity.status(HttpStatus.OK)
                    .body(societiesInformationGetter.getAllSocieties().stream().map(modelMapperSociety::convertSocietyToDtoOut).collect(Collectors.toList()));
    }

    @Operation(summary = "Get society by his name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "society successfully returned "),
            @ApiResponse(responseCode = "404", description = "society with given name not found"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @GetMapping(path = "/name/{societyName}", consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<SocietyDtoOut> getSocietyByName(@PathVariable String societyName) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperSociety.convertSocietyToDtoOut(societiesInformationGetter.getSocietyByName(societyName)));
        } catch (ResourceNotFoundException resourceNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Get society by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "society successfully returned "),
            @ApiResponse(responseCode = "404", description = "society with given name not found"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @GetMapping(path = "/{societyId}", consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<SocietyDtoOut> getSocietyById(@PathVariable UUID societyId) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperSociety.convertSocietyToDtoOut(societiesInformationGetter.getSocietyById(societyId)));
        } catch (ResourceNotFoundException resourceNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Get all advantages from a society by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Advantages successfully returned for a society"),
            @ApiResponse(responseCode = "404", description = "society with given name not found"),
    })
    @GetMapping(path = "/{societyId}/advantages", consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<List<AdvantageDtoOut>> getAllAdvantageBySociety(@PathVariable UUID societyId) {
        try {
            Society society = societyGetter.getSocietyById(societyId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(advantageGetter.getAllAdvantageBySociety(society).stream().map(modelMapperAdvantage::convertAdvantageToDto).collect(Collectors.toList()));
        } catch (ResourceNotFoundException resourceNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Get all advantages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Advantages successfully returned"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @GetMapping(path = "/advantages", consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<List<AdvantageDtoOut>> getAllAdvantages() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(advantageGetter.getAllAdvantages().stream().map(modelMapperAdvantage::convertAdvantageToDto).collect(Collectors.toList()));
    }


    @Operation(summary = "Get all fav societies from a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "societies successfully returned "),
            @ApiResponse(responseCode = "404", description = "Consumer with given name not found"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @GetMapping(path = "/fav/{consumerId}", consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<List<SocietyDtoOut>> getFavSocieties(@PathVariable UUID consumerId) {
        try {
            Consumer consumer = consumerGetter.getConsumerById(consumerId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(consumerInformationGiver.getFavSocieties(consumer).stream().map(modelMapperSociety::convertSocietyToDtoOut).collect(Collectors.toList()));
        } catch (ResourceNotFoundException resourceNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Get all advantage available for a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "20O", description = "Advantages successfully returned"),
            @ApiResponse(responseCode = "404", description = "Consumer with given name not found"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @GetMapping(path = "/advantages-available/{consumerId}", consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<List<AdvantageDtoOut>> getAllAdvantageEligible(@PathVariable UUID consumerId) {
        try {
            Consumer consumer = consumerGetter.getConsumerById(consumerId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(consumerInformationGiver.getAllAdvantageEligible((consumer)).stream().map(modelMapperAdvantage::convertAdvantageToDto).collect(Collectors.toList()));
        } catch (ResourceNotFoundException resourceNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
