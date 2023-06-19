package com.serviceType.services.models;

import lombok.*;
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
