package fr.univcotedazur.multifidelity.controllers;

import fr.univcotedazur.multifidelity.constant.RouteConstants;
import fr.univcotedazur.multifidelity.controllers.dto_out.ErrorDto;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.SocietyGetter;
import fr.univcotedazur.multifidelity.interfaces.StatisticGiver;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = RouteConstants.StatisticService.BASE_STATISTICS_ROUTE, produces = APPLICATION_JSON_VALUE)
@Tag(name = "StatisitcsService Controller", description = "Controller handling statisitcs")
public class StatisticsServiceController {

    @Autowired
    private StatisticGiver statisticGiver;

    @Autowired
    private SocietyGetter societyGetter;

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorDto handleExceptions(MethodArgumentNotValidException e) {
        ErrorDto errorDTO = new ErrorDto();
        errorDTO.setError("Cannot process statistic information because the request is not valid");
        errorDTO.setDetails(e.getMessage());
        return errorDTO;
    }

    @Operation(summary = "Get profit by society")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "statistics for a society successfully returned"),
            @ApiResponse(responseCode = "404", description = "society with given id was not found"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @GetMapping(path = "/society-profits/{societyId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Float> getProfitBySocietyId(@PathVariable UUID societyId) {
        try {
            Society society = societyGetter.getSocietyById(societyId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(statisticGiver.getProfitBySocietyId(society));
        } catch (ResourceNotFoundException  resourceNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Get profit made by the platform in one city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "statistics successfully returned"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @GetMapping(path = "/profits", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Float> getProfit() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(statisticGiver.getProfit());
    }
}
