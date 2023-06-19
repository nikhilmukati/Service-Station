package com.vehicle.vehicleService.service.interfaces;

import com.vehicle.vehicleService.model.Vehicle;

import java.util.List;


public interface VehicleService {
     List<Vehicle> getAllVehicle();

     Vehicle getVehicle(int vehicleId);

     List<Vehicle> getVehicleByCustomerId(int customerId);

     Vehicle save(Vehicle vehicle);

     Vehicle acceptVehicle(int vehicleId);
}
