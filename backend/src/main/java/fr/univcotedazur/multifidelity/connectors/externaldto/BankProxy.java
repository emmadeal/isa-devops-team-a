package fr.univcotedazur.multifidelity.connectors.externaldto;

import fr.univcotedazur.multifidelity.entities.Consumer;
import fr.univcotedazur.multifidelity.exceptions.BankRefusedPaymentException;
import fr.univcotedazur.multifidelity.interfaces.Bank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Component
public class BankProxy implements Bank {

    @Override
    public boolean importMoney(Consumer consumer, float value) {
        return false;
    }

    /*
        @Value("${bank.host.baseurl}")
    private String bankHostandPort;

    private RestTemplate restTemplate = new RestTemplate();


    @Override
    public boolean pay(Consumer customer, double value) {
        try {
            ResponseEntity<PaymentDTO> result = restTemplate.postForEntity(
                    bankHostandPort + "/cctransactions",
                    new PaymentDTO(customer.getCreditCard(), value),
                    PaymentDTO.class
            );
            return (result.getStatusCode().equals(HttpStatus.CREATED));
        }
        catch (HttpClientErrorException errorException) {
            if (errorException.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                return false;
            }
            throw errorException;
        }
    }

     */
}
