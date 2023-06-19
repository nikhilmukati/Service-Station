package com.appointment.bookingService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ServiceType {
    int serviceId;
    String serviceType;
    LocalTime timeRequired;
}
