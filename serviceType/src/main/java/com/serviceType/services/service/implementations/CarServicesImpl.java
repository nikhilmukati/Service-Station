package com.serviceType.services.service.implementations;

import com.serviceType.services.models.ServiceType;
import com.serviceType.services.service.interfaces.Services;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.function.Consumer;

@Service("carServicesImpl")
public class CarServicesImpl implements Services {

    Map<Integer, ServiceType> serviceTypeMap = new HashMap<>();

    {
        serviceTypeMap.put(1, new ServiceType(1, "Oil Change", LocalTime.of(0,30)));
        serviceTypeMap.put(2, new ServiceType(2, "Air,Oil,Fuel Filter Cleaning/Replacement",
                LocalTime.of(0,45)));
        serviceTypeMap.put(3, new ServiceType(3, "Spark Plug Replacement ", LocalTime.of(0,59)));
        serviceTypeMap.put(4, new ServiceType(4, "Brake Pad Replacement", LocalTime.of(0,59)));
        serviceTypeMap.put(5, new ServiceType(5, "Tire Rotation and Balancing", LocalTime.of(0,45)));
        serviceTypeMap.put(6, new ServiceType(6, "Wheel Alignment", LocalTime.of(1,0)));
        serviceTypeMap.put(7, new ServiceType(7, "Battery Check and Replacement", LocalTime.of(0,30)));
        serviceTypeMap.put(8, new ServiceType(8, "Coolant Flush and Refill ", LocalTime.of(0,59)));
        serviceTypeMap.put(9, new ServiceType(9, "Transmission Fluid Flush ", LocalTime.of(0,59)));
        serviceTypeMap.put(10, new ServiceType(10, "Engine Diagnostic ", LocalTime.of(0,45)));
    }

    @Override
    public ServiceType getService(int serviceId) {
        return serviceTypeMap.get(serviceId);
    }

    @Override
    public List<ServiceType> getAllServices() {
        return new ArrayList<>(serviceTypeMap.values());
    }

    @Override
    public List<ServiceType> addService(List<ServiceType> serviceType) {
        Optional.ofNullable(serviceType).ifPresentOrElse((serviceTypes) ->
                serviceTypes.forEach(service -> serviceTypeMap.put(service.getServiceId(), service)),
                RuntimeException::new
        );
        return serviceType;
    }
}
