package com.appointment.bookingService.customExceptions;

public class VehicleValidationFailed extends RuntimeException{
    public VehicleValidationFailed(String message) {
        super(message);
    }
}
