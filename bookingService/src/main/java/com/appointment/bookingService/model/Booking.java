package com.appointment.bookingService.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booking {
    private int bookingId;
    private int customerId;
    private int vehicleId;
    private String vehicleType;
    private List<String> requiredServices;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private LocalDate appointmentEndDate;
    private  LocalTime appointmentEndTime;
    private LocalTime bookedTime;
}
