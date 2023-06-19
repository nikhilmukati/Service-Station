package com.vehicle.vehicleService.customExceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
public class NoVehicleFoundException extends RuntimeException{

    public NoVehicleFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
