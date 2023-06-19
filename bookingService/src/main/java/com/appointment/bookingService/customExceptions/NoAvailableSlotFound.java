package com.appointment.bookingService.customExceptions;

public class NoAvailableSlotFound extends RuntimeException{
    public NoAvailableSlotFound(String message) {
        super(message);
    }


}
