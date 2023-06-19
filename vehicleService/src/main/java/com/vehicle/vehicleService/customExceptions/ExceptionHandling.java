package com.vehicle.vehicleService.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(NoVehicleFoundException.class)
    public ResponseEntity<Object> noVehicleFoundExceptionHandler(NoVehicleFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<Object> entityAlreadyExistExceptionHandler(EntityAlreadyExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> validationExceptionHandler(ValidationException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }
}
