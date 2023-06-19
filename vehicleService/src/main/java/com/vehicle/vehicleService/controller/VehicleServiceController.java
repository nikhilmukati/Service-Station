package com.vehicle.vehicleService.controller;

import com.vehicle.vehicleService.model.Vehicle;
import com.vehicle.vehicleService.service.interfaces.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Vehicle-Service")
public class VehicleServiceController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicle(){
        return new ResponseEntity<>(vehicleService.getAllVehicle(), HttpStatus.OK);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable(name = "vehicleId", required = true) int vehicleId){
        return new ResponseEntity<>(vehicleService.getVehicle(vehicleId), HttpStatus.OK);
    }

    @PostMapping("/vehicle/register")
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody() Vehicle vehicle){
        vehicleService.save(vehicle);
        return new ResponseEntity<>( vehicle, HttpStatus.CREATED);
    }

    @GetMapping("/customer/vehicle/{customerId}")
    public ResponseEntity<List<Vehicle>> getVehicleByCustomerId(@PathVariable int customerId){
        return new ResponseEntity<>(vehicleService.getVehicleByCustomerId(customerId), HttpStatus.OK);
    }

    @GetMapping("/accept/vehicle/{vehicleId}")
    public ResponseEntity<Vehicle> acceptVehicleForService(@PathVariable int vehicleId){
        return new ResponseEntity<>(vehicleService.acceptVehicle(vehicleId), HttpStatus.OK);
    }

}
