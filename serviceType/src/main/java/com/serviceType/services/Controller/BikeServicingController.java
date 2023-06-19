package com.serviceType.services.Controller;

import com.serviceType.services.models.ServiceType;
import com.serviceType.services.service.implementations.BikeServicesImpl;
import com.serviceType.services.service.interfaces.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Vehicle-Service/bike/")
public class BikeServicingController {

    @Autowired
    @Qualifier("bikeServicesImpl")
    private Services bikeServices;

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<ServiceType> getServiceType(@PathVariable int serviceId){
        ServiceType serviceType = bikeServices.getService(serviceId);
        return  new ResponseEntity<ServiceType>(serviceType, HttpStatus.OK);
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServiceType>> getAllServices(){
        return  new ResponseEntity<>(bikeServices.getAllServices(), HttpStatus.OK);
    }

    @PostMapping("/service/add")
    public ResponseEntity<List<ServiceType>> addService(@RequestBody List<ServiceType> serviceType){
        List<ServiceType> newServiceType = bikeServices.addService(serviceType);
        return new ResponseEntity<>(newServiceType, HttpStatus.CREATED);
    }
}
