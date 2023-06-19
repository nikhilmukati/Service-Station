package com.serviceType.services.service.implementations;

import com.serviceType.services.models.ServiceType;
import com.serviceType.services.service.interfaces.Services;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;

@Service("bikeServicesImpl")
public class BikeServicesImpl implements Services {

    Map<Integer, ServiceType> serviceTypeMap = new HashMap<>();

    {
        serviceTypeMap.put(1, new ServiceType(1, "Oil Change", LocalTime.of(0,20)));
        serviceTypeMap.put(2, new ServiceType(2, "Air,Oil,Fuel Filter Cleaning/Replacement",
                LocalTime.of(0,30)));
        serviceTypeMap.put(3, new ServiceType(3, "Spark Plug Replacement ", LocalTime.of(0,30)));
        serviceTypeMap.put(4, new ServiceType(4, "Brake Pad Replacement", LocalTime.of(0,30)));
        serviceTypeMap.put(5, new ServiceType(5, "Chain Lubrication and Adjustment", LocalTime.of(0,20)));
        serviceTypeMap.put(6, new ServiceType(6, "Tire Inspection and Pressure Check ", LocalTime.of(0,15)));
        serviceTypeMap.put(7, new ServiceType(7, "Battery Check and Replacement", LocalTime.of(0,20)));
        serviceTypeMap.put(8, new ServiceType(8, "Carburetor Cleaning and Tuning", LocalTime.of(0,45)));
        serviceTypeMap.put(9, new ServiceType(9, "Transmission Fluid Flush ", LocalTime.of(0,59)));
        serviceTypeMap.put(10, new ServiceType(10, "Electrical System Check", LocalTime.of(0,30)));
        serviceTypeMap.put(11, new ServiceType(11, "Wheel Alignment ", LocalTime.of(0,30)));
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
    public List<ServiceType> addService(List<ServiceType> serviceTypes) {
        Optional.ofNullable(serviceTypes).ifPresentOrElse((serviceType) ->
                serviceType.forEach(service -> serviceTypeMap.put(service.getServiceId(), service)),
                () -> { throw new RuntimeException("Null entry not Expected!"); }
        );
        return serviceTypes;
    }
}
