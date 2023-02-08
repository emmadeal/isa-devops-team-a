package fr.univcotedazur.multifidelity.controllers;

import fr.univcotedazur.multifidelity.constant.RouteConstants;
import fr.univcotedazur.multifidelity.controllers.dto_out.ErrorDto;
import fr.univcotedazur.multifidelity.controllers.dto_out.SelectedAdvantageDtoOut;
import fr.univcotedazur.multifidelity.entities.SelectedAdvantage;
import fr.univcotedazur.multifidelity.exceptions.NotAvailableAdvantageException;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.AdvantageGetter;
import fr.univcotedazur.multifidelity.interfaces.CollectGift;
import fr.univcotedazur.multifidelity.modelMapper.ModelMapperSelectedAdvantage;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@RequestMapping(path = RouteConstants.CounterCollectRoutes.BASE_COLLECT_ROUTE, produces = APPLICATION_JSON_VALUE)
@Tag(name = "Counter collect Controller", description = "Controller handling counter collect")
public class CounterCollectController {

    @Autowired
    private CollectGift collectGift;

    @Autowired
    private AdvantageGetter advantageGetter;

    @Autowired
    private ModelMapperSelectedAdvantage modelMapperSelectedAdvantage;


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorDto handleExceptions(MethodArgumentNotValidException e) {
        ErrorDto errorDTO = new ErrorDto();
        errorDTO.setError("Cannot process collect gift information because the request is not valid");
        errorDTO.setDetails(e.getMessage());
        return errorDTO;
    }


    @Operation(summary = "recovery of a gift in store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully received gift "),
            @ApiResponse(responseCode = "404", description = "No gift found for the given id"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
            @ApiResponse(responseCode = "421", description = "the gift has already been received "),
    })
    @PutMapping( consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<SelectedAdvantageDtoOut> giveGift(@RequestBody UUID selectedAdvantageId) {
        try {
            log.info("modification of selected advantage to RETRY state with id: {}",selectedAdvantageId);
            SelectedAdvantage selectedAdvantage = advantageGetter.getSelectedAdvantageById(selectedAdvantageId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperSelectedAdvantage.convertSelectedAdvantageToDto(collectGift.giveGift(selectedAdvantage)));
        } catch (ResourceNotFoundException resourceNotFoundException) {
            log.error("No selected advantage found for id: {}",selectedAdvantageId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (NotAvailableAdvantageException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
