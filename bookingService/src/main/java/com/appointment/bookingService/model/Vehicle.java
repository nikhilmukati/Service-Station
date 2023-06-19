package com.appointment.bookingService.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Vehicle {
    private int vehicleId;
    private int customerId;
    private String brand;
    private String model;
    private Year year;
    private String type;
    private String registrationNumber;
    private String engineNumber;
    private Boolean isInsured;
    private Boolean accepted;
    private Boolean delivered;
}
