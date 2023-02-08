package fr.univcotedazur.multifidelity.exceptions;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ResourceNotFoundException extends Exception {

    private static final String PREFIX = "Resource not found exception: ";

    public ResourceNotFoundException (String msg) {
        super(PREFIX + msg);
    }


}
