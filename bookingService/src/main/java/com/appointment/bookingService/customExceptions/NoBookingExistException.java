package com.appointment.bookingService.customExceptions;

public class NoBookingExistException extends RuntimeException{
    public NoBookingExistException(String message) {
        super(message);
    }
}
