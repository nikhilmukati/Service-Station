package com.appointment.bookingService.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcptionHandling {

    @ExceptionHandler(NoAvailableSlotFound.class)
    public ResponseEntity<Object> noAvilableSlotFoundException(NoAvailableSlotFound e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookingAlradyExistException.class)
    public ResponseEntity<Object> noAvilableSlotFoundException(BookingAlradyExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoBookingExistException.class)
    public ResponseEntity<Object> noBookingExistException(NoBookingExistException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VehicleValidationFailed.class)
    public ResponseEntity<Object> vehicleValidationFailedException(VehicleValidationFailed e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
