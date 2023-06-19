package com.serviceType.services.service.interfaces;

import com.serviceType.services.models.ServiceType;
import java.util.List;

public interface Services {

    ServiceType getService(int serviceId);

    List<ServiceType> getAllServices();

    List<ServiceType> addService(List<ServiceType> serviceType);
}
