package com.appointment.bookingService.customExceptions;

public class BookingAlradyExistException extends  RuntimeException{
    public BookingAlradyExistException(String message) {
        super(message);
    }
}
