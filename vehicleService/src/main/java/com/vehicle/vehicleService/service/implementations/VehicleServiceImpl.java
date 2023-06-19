package com.vehicle.vehicleService.service.implementations;

import com.vehicle.vehicleService.customExceptions.EntityAlreadyExistException;
import com.vehicle.vehicleService.customExceptions.NoVehicleFoundException;
import com.vehicle.vehicleService.customExceptions.ValidationException;
import com.vehicle.vehicleService.model.Vehicle;
import com.vehicle.vehicleService.service.interfaces.VehicleService;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    List<Vehicle> vehicleList = new ArrayList<>();

    {
        vehicleList.add(new Vehicle(1, 1, "Bajaj", "180",  Year.of(2017), "bike","AB-12-AB-1234", "ac11aa1211", false, false, false));
        vehicleList.add(new Vehicle(2, 2, "Honda", "150", Year.of(2018), "bike","BC-11-BC-1235", "ac12aa1212", false, false, false));
        vehicleList.add(new Vehicle(3, 1, "Honda", "300", Year.of(2019), "car","CD-13-CD-1236", "ac13aa1213", true, false, false));
    };

    @Override
    public List<Vehicle> getAllVehicle() {
        return vehicleList;
    }

    @Override
    public Vehicle getVehicle(int vehicleId) {
        return vehicleList.stream()
                .filter(vehicle -> vehicleId==vehicle.getVehicleId())
                .findAny()
                .orElseThrow(()->new NoVehicleFoundException("No Vehicle Found!"));
    }

    @Override
    public List<Vehicle> getVehicleByCustomerId(int customerId) {
        List<Vehicle> vehicles = vehicleList.stream()
                .filter(vehicle -> customerId == vehicle.getCustomerId())
                .collect(Collectors.toList());
        return Optional.of(vehicles).orElseThrow(() -> {
            throw new NoVehicleFoundException("No Vehicle Found!");
        });
    }

    @Override
    public Vehicle save(Vehicle newVehicle) {
        newVehicle.setVehicleId(vehicleList.size()+1);
        if (vehicleList.stream()
                .anyMatch(vehicle->vehicle.getEngineNumber().equalsIgnoreCase(newVehicle.getEngineNumber()))
                &&
            vehicleList.stream().anyMatch(vehicle -> vehicle.getRegistrationNumber().equalsIgnoreCase(newVehicle.getRegistrationNumber()))
        )
             throw new EntityAlreadyExistException("Entity Already Exist!");
        validateVehicleRequest(newVehicle);
        isVehicleInsured(newVehicle);
        this.vehicleList.add(newVehicle);
        return newVehicle;

    }

    @Override
    public Vehicle acceptVehicle(int vehicleId) {

        Vehicle vehicle = vehicleList.stream()
                .filter(vehicle1 -> vehicle1.getVehicleId() == vehicleId)
                .findFirst()
                .orElseThrow(NoVehicleFoundException::new);
        if(initiatePoliceVerifiction(vehicle))
            vehicle.setAccepted(true);
        return vehicle;
    }

    private boolean initiatePoliceVerifiction(Vehicle vehicle) {
        if (vehicle.getRegistrationNumber() != null && vehicle.getEngineNumber() != null) {
            if (vehicle.getRegistrationNumber().matches("[A-Z]{2}-\\d{2}-[A-Z]{2}-\\d{4}") &&
                    vehicle.getEngineNumber().matches("[A-Z0-9a-z\\d]{10}")
            ) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void isVehicleInsured(Vehicle newVehicle) {
        String regiNumber = newVehicle.getRegistrationNumber();
        String engineNumber = newVehicle.getEngineNumber();
        int lastRegiNumber = 0, lastEngiNumber = 0;
        try {
            lastRegiNumber = Integer.parseInt(regiNumber.substring(regiNumber.length() - 4));
            lastEngiNumber = Integer.parseInt(regiNumber.substring(engineNumber.length() - 4));

            if(lastRegiNumber<=1111 && lastRegiNumber>=6666)
                newVehicle.setIsInsured(false);
            if (lastEngiNumber<=1111 && lastEngiNumber>=7777)
                newVehicle.setIsInsured(false);

        }catch (NumberFormatException e){
            throw  new ValidationException("Valid Details not Provided!");
        }
        newVehicle.setIsInsured(true);
    }

    public static void validateVehicleRequest(Vehicle vehicle) {

        if (vehicle.getCustomerId() <= 0) {
            throw new ValidationException("Invalid customerId");
        }

        if (vehicle.getBrand() == null || vehicle.getBrand().isEmpty()) {
            throw new ValidationException("Invalid brand");
        }

        if (vehicle.getModel() == null || vehicle.getModel().isEmpty()) {
            throw new ValidationException("Invalid model");
        }

        if (vehicle.getYear() == null || !isValidYear(vehicle.getYear())) {
            throw new ValidationException("Invalid year");
        }

        if (vehicle.getRegistrationNumber() == null || vehicle.getRegistrationNumber().isEmpty() &&
                !vehicle.getRegistrationNumber().matches("[A-Z]{2}-\\d{2}-[A-Z]{2}-\\d{4}")
        ) {
            System.out.println("Invalid registrationNumber");
        }

        if (vehicle.getEngineNumber() == null || vehicle.getEngineNumber().isEmpty() && !vehicle.getEngineNumber().matches("[A-Z\\d]{6}")) {
            throw new ValidationException("Invalid engineNumber");
        }
    }

    private static boolean isValidYear(Year year) {
        int currentYear = Year.now().getValue();
        int vehicleYear = year.getValue();
        return vehicleYear <= currentYear;
    }
}
