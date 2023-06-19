package com.appointment.bookingService.externalService;


import com.appointment.bookingService.model.Vehicle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8082/Vehicle-Service",  name = "Vehicle-Service")
public interface VehicleService {
    @GetMapping("/vehicles")
    List<Vehicle> getAllVehicle();

    @GetMapping("/vehicle/{vehicleId}")
    Vehicle getVehicle(int vehicleId);

    @PostMapping("/vehicle/register")
    Vehicle save(Vehicle vehicle);

    @GetMapping("/customer/vehicle/{customerId}")
    List<Vehicle> getVehicleByCustomerId(@PathVariable("customerId") int customerId);

    @GetMapping("/accept/vehicle/{vehicleId}")
    Vehicle acceptVehicleForService(@PathVariable int vehicleId);


}
