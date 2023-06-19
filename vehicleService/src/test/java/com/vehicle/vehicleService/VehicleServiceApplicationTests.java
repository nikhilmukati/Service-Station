package com.vehicle.vehicleService;

import com.vehicle.vehicleService.model.Vehicle;
import com.vehicle.vehicleService.service.interfaces.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class VehicleServiceApplicationTests {

	@Mock
	private VehicleService vehicleService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllVehicle() {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(new Vehicle(1, 1, "Bajaj", "180",  Year.of(2017), "bike","ab12ab1234", "ac11aa1211", false, false, false));
		vehicles.add(new Vehicle(2, 2, "Bajaj", "180",  Year.of(2018), "bike","ab12ab1234", "ac11aa1211", false, false, false));
		when(vehicleService.getAllVehicle()).thenReturn(vehicles);

		List<Vehicle> result = vehicleService.getAllVehicle();

		assertEquals(vehicles.size(), result.size());
	}

	@Test
	public void testGetVehicle() {
		int vehicleId = 1;
		Vehicle vehicle = new Vehicle(1, 1, "Bajaj", "180",  Year.of(2017), "bike","ab12ab1234", "ac11aa1211", false, false, false);
		when(vehicleService.getVehicle(vehicleId)).thenReturn(vehicle);

		Vehicle result = vehicleService.getVehicle(vehicleId);

		assertEquals(vehicle, result);
	}

	@Test
	public void testGetVehicleByCustomerId() {
		int customerId = 1;
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(new Vehicle(1, 1, "Bajaj", "180",  Year.of(2017), "bike","ab12ab1234", "ac11aa1211", false, false, false));
		vehicles.add(new Vehicle(2, 2, "Bajaj", "180",  Year.of(2018), "bike","ab12ab1234", "ac11aa1211", false, false, false));
		when(vehicleService.getVehicleByCustomerId(customerId)).thenReturn(vehicles);

		List<Vehicle> result = vehicleService.getVehicleByCustomerId(customerId);

		assertEquals(vehicles.size(), result.size());
	}

	@Test
	public void testSaveVehicle() {
		Vehicle vehicle = new Vehicle(1, 1, "Bajaj", "180",  Year.of(2017), "bike","ab12ab1234", "ac11aa1211", false, false, false);
		when(vehicleService.save(vehicle)).thenReturn(vehicle);

		Vehicle result = vehicleService.save(vehicle);

		assertEquals(vehicle, result);
	}

	@Test
	public void testAcceptVehicle() {
		int vehicleId = 1;
		Vehicle vehicle = new Vehicle(1, 1, "Bajaj", "180",  Year.of(2017), "bike","ab12ab1234", "ac11aa1211", false, true, false);
		when(vehicleService.acceptVehicle(vehicleId)).thenReturn(vehicle);

		Vehicle result = vehicleService.acceptVehicle(vehicleId);
		assertTrue(result.getAccepted());
	}

}
