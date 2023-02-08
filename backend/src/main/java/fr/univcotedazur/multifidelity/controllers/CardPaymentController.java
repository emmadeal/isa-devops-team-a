package fr.univcotedazur.multifidelity.controllers;

import fr.univcotedazur.multifidelity.components.PaymentService;
import fr.univcotedazur.multifidelity.constant.RouteConstants;
import fr.univcotedazur.multifidelity.controllers.dto_out.ErrorDto;
import fr.univcotedazur.multifidelity.controllers.dto_out.PurchaseDtoOut;
import fr.univcotedazur.multifidelity.exceptions.NotEnoughBalanceException;
import fr.univcotedazur.multifidelity.exceptions.ResourceNotFoundException;
import fr.univcotedazur.multifidelity.modelMapper.ModelMapperPurchase;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@RequestMapping(path = RouteConstants.CardRoutes.BASE_CARD_ROUTE, produces = APPLICATION_JSON_VALUE)
@Tag(name = "Card Controller", description = "Controller handling cards")
public class CardPaymentController {

    @Autowired
    private PaymentService cardPayment;

    @Autowired
    private ModelMapperPurchase modelMapperPurchase;


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorDto handleExceptions(MethodArgumentNotValidException e) {
        ErrorDto errorDTO = new ErrorDto();
        errorDTO.setError("Cannot process card payment information because the request is not valid");
        errorDTO.setDetails(e.getMessage());
        return errorDTO;
    }

    @Operation(summary = "Realize a payment with the fidelity card in a partner society")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase successful"),
            @ApiResponse(responseCode = "401", description = "Purchase could not be completed because the balance is too low "),
    })
    @PostMapping(path ="card/",consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<PurchaseDtoOut> buyInSocietyWithFidelityCard(@RequestBody @Valid PurchaseDtoOut purchaseDTO)  {
        try{
            log.info("adding a purchase made with a loyalty card");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperPurchase.convertPurchaseToDto(cardPayment.buyInSocietyWithFidelityCard(modelMapperPurchase.convertDtoToPurchase(purchaseDTO))));
        }catch (NotEnoughBalanceException | ResourceNotFoundException notEnoughBalanceException){
            log.error("the payment of the purchase is not authorized, the balance on the card is too low");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(summary = "Realize a payment in a partner society")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase successful"),
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE) // path is a REST CONTROLLER NAME
    public ResponseEntity<PurchaseDtoOut> buyInSociety(@RequestBody @Valid PurchaseDtoOut purchaseDTO) throws ResourceNotFoundException {
        log.info("adding a purchase");
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapperPurchase.convertPurchaseToDto(cardPayment.buyInSociety(modelMapperPurchase.convertDtoToPurchase(purchaseDTO))));
    }

}