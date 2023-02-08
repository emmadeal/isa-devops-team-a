package fr.univcotedazur.multifidelity.controllers;

import fr.univcotedazur.multifidelity.constant.RouteConstants;
import fr.univcotedazur.multifidelity.controllers.dto_in.ConsumerDtoIn;
import fr.univcotedazur.multifidelity.controllers.dto_out.ConsumerDtoOut;
import fr.univcotedazur.multifidelity.controllers.dto_out.ErrorDto;
import fr.univcotedazur.multifidelity.controllers.dto_out.SelectedAdvantageDtoOut;
import fr.univcotedazur.multifidelity.entities.Advantage;
import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.entities.Society;
import fr.univcotedazur.multifidelity.exceptions.AccountNotFoundException;
import fr.univcotedazur.multifidelity.exceptions.AlreadyExistingAccountException;
import fr.univcotedazur.multifidelity.exceptions.BankRefusedPaymentException;
import fr.univcotedazur.multifidelity.exceptions.IWYPLSConnexionException;
import fr.univcotedazur.multifidelity.exceptions.NotEnoughPointsException;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.interfaces.AdvantageChoices;
import fr.univcotedazur.multifidelity.interfaces.AdvantageGetter;
import fr.univcotedazur.multifidelity.interfaces.CardUsage;
import fr.univcotedazur.multifidelity.interfaces.ConsumerGetter;
import fr.univcotedazur.multifidelity.interfaces.ConsumerModifier;
import fr.univcotedazur.multifidelity.interfaces.RegistryConsumerProcessor;
import fr.univcotedazur.multifidelity.interfaces.SocietyGetter;
import fr.univcotedazur.multifidelity.modelMapper.ModelMapperConsumer;
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
@RequestMapping(path = RouteConstants.ConsumerRoutes.BASE_CONSUMER_ROUTE, produces = APPLICATION_JSON_VALUE)
@Tag(name = "Consumer Controller", description = "Controller handling consumers")
public class ConsumerController {

    @Autowired
    private  RegistryConsumerProcessor registryConsumerProcessor;

    @Autowired
    private  ConsumerModifier consumerModifier;

    @Autowired
    private  AdvantageChoices advantageChoices;

    @Autowired
    private CardUsage cardUsage;

    @Autowired
    private AdvantageGetter advantageGetter;

    @Autowired
    private SocietyGetter societyGetter;

    @Autowired
    private ConsumerGetter consumerGetter;

    @Autowired
    private ModelMapperConsumer modelMapperConsumer;

    @Autowired
    private ModelMapperSelectedAdvantage modelMapperSelectedAdvantage;



    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorDto handleExceptions(MethodArgumentNotValidException e) {
        ErrorDto errorDTO = new ErrorDto();
        errorDTO.setError("Cannot process Consumer information because the request is not valid");
        errorDTO.setDetails(e.getMessage());
        return errorDTO;
    }


    @Operation(summary = "Create a new consumer with an email and a password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creation of the consumer successfully"),
            @ApiResponse(responseCode = "409", description = "this email is already in use by an another account "),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @PostMapping(path = "/sign-in", consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<ConsumerDtoOut> signIn(@RequestBody @Valid ConsumerDtoIn consumerDtoIn) {
        try {
            log.info("creation of a consumer account");
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(modelMapperConsumer.convertConsumerToDto(registryConsumerProcessor.signIn(consumerDtoIn.getEmail(), consumerDtoIn.getPassword())));
        } catch (AlreadyExistingAccountException existingAccountException) {
            log.error("failed to create a new account, login details are already in use ");
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


    @Operation(summary = "LogIn of a existing consumer with his email and his password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "LogIn of the consumer successfully"),
            @ApiResponse(responseCode = "404", description = "A consumer with this email and this password was not found"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @PostMapping(path = "/log-in", consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<ConsumerDtoOut> logIn(@RequestBody @Valid ConsumerDtoIn consumerDtoIn) {
        try {
            log.info("connection of a consumer");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperConsumer.convertConsumerToDto(registryConsumerProcessor.logIn(consumerDtoIn.getEmail(), consumerDtoIn.getPassword())));
        } catch (AccountNotFoundException accountNotFoundException) {
            log.error("connection failure");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }




    @Operation(summary = "update a consumer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consumer updated successfully"),
            @ApiResponse(responseCode = "404", description = "The consumer with given id is not found"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @PutMapping(path ="/{consumerId}",consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsumerDtoOut> updateConsumer(@PathVariable UUID consumerId, @RequestBody @Valid ConsumerDtoIn consumerDtoIn) {
        try{
            log.info("modification of a consumer with the id : {}",consumerId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperConsumer.convertConsumerToDto(consumerModifier.updateConsumer(consumerId,modelMapperConsumer.convertDtoInToConsumer(consumerDtoIn))));
        }catch (ResourceNotFoundException resourceNotFoundException){
            log.error("No consumer found for id: {}",consumerId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @Operation(summary = "add fav society to a consumer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "society added to fav successfully"),
            @ApiResponse(responseCode = "404", description = "The consumer or the society is not found"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @PostMapping(path ="/{consumerId}/fav-society",consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<ConsumerDtoOut> addFavSociety(@PathVariable UUID consumerId, @RequestBody UUID  societyId) {
        try{
            log.info("add a society to the customer's favorites with id : {}",societyId);
            Consumer consumer = consumerGetter.getConsumerById(consumerId);
            Society society = societyGetter.getSocietyById(societyId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperConsumer.convertConsumerToDto(consumerModifier.addFavSociety(society,consumer)));
        }catch (ResourceNotFoundException resourceNotFoundException){
            log.error("No consumer/society found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "delete fav society to a consumer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "society deleted to fav successfully"),
            @ApiResponse(responseCode = "404", description = "The consumer or the society is not found"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @DeleteMapping(path ="/{consumerId}/fav-society/{societyId}", consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<ConsumerDtoOut> deleteFavSociety(@PathVariable UUID consumerId , @PathVariable UUID  societyId) {
        try{
            log.info("delete a society to the customer's favorites with id : {}",societyId);
            Consumer consumer = consumerGetter.getConsumerById(consumerId);
            Society society =  societyGetter.getSocietyById(societyId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperConsumer.convertConsumerToDto(consumerModifier.deleteFavSociety(society,consumer)));
        }catch (ResourceNotFoundException resourceNotFoundException){
            log.error("No consumer/society found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @Operation(summary = "delete fav society to a consumer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "society deleted to fav successfully"),
            @ApiResponse(responseCode = "404", description = "The consumer or the advantage is not found"),
            @ApiResponse(responseCode = "401", description = "The bank payment failed"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @PutMapping(path ="/{consumerId}/balance", consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<Float> reloadBalance(@RequestBody float amount,@PathVariable UUID consumerId) {
        try{
            log.info("adding money to loyalty card balance of a consumer");
            Consumer consumer = consumerGetter.getConsumerById(consumerId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cardUsage.reloadBalance(amount,consumer));
        }catch (BankRefusedPaymentException bankRefusedPaymentException){
            log.error("adding money is not authorized by the bank");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (ResourceNotFoundException e) {
            log.error("No consumer found for id: {}",consumerId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "consumer select an advantage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "creation of a successful advantage selection"),
            @ApiResponse(responseCode = "404", description = "The consumer or the advantage is not found"),
            @ApiResponse(responseCode = "401", description = "you don't have any points"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @PostMapping(path ="/{consumerId}/select-advantage", consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<SelectedAdvantageDtoOut> selectAdvantage(@RequestBody UUID advantageId, @PathVariable UUID consumerId) {
        try{
            Consumer consumer = consumerGetter.getConsumerById(consumerId);
            Advantage advantage = advantageGetter.getAdvantageById(advantageId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(modelMapperSelectedAdvantage.convertSelectedAdvantageToDto(advantageChoices.selectAdvantage(advantage,consumer)));
        }catch (NotEnoughPointsException notEnoughPointsException){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @Operation(summary = "consumer use an advantage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "use of a successfully selected avatage"),
            @ApiResponse(responseCode = "404", description = "The consumer or the society is not found"),
            @ApiResponse(responseCode = "401", description = "iwypls connexion failed"),
            @ApiResponse(responseCode = "422", description = "the request is not valid"),
    })
    @PutMapping(path ="/{consumerId}/use-advantage", consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<SelectedAdvantageDtoOut> useAdvantage(@RequestBody UUID advantageId, @PathVariable UUID consumerId) {
        try{
            Consumer consumer = consumerGetter.getConsumerById(consumerId);
            Advantage advantage = advantageGetter.getAdvantageById(advantageId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(modelMapperSelectedAdvantage.convertSelectedAdvantageToDto(advantageChoices.useAdvantage(advantage,consumer)));
        }catch (IWYPLSConnexionException iwyplsConnexionException){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

