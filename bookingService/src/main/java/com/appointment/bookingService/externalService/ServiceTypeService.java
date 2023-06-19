package com.appointment.bookingService.externalService;

import com.appointment.bookingService.model.ServiceType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="ServiceType-Service", url="http://localhost:8083/Vehicle-Service/")
public interface ServiceTypeService {

    @GetMapping("bike/service/{serviceId}")
    ServiceType getBikeService(int serviceId);

    @GetMapping("bike/services")
    List<ServiceType> getAllBikeServices();

    @GetMapping("bike/service/add")
    List<ServiceType> addBikeService(List<ServiceType> serviceType);

    @GetMapping("car/service/{serviceId}")
    ServiceType getCarService(int serviceId);

    @GetMapping("car/services")
    List<ServiceType> getAllCarServices();

    @GetMapping("car/service/add")
    List<ServiceType> addCarService(List<ServiceType> serviceType);
}
