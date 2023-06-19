package com.serviceType.services;

import com.serviceType.services.models.ServiceType;
import com.serviceType.services.service.implementations.BikeServicesImpl;
import com.serviceType.services.service.implementations.CarServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ServiceTypeApplicationTests {

	@Mock
	private BikeServicesImpl bikeServices;

	@Mock
	private CarServicesImpl carServices;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetService() {
		int serviceId = 1;
		ServiceType serviceType = new ServiceType(serviceId, "Oil Change", LocalTime.of(0,50));
		when(carServices.getService(serviceId)).thenReturn(serviceType);

		ServiceType result = carServices.getService(serviceId);

		assertEquals(serviceType, result);
		verify(carServices, times(1)).getService(serviceId);
	}

	@Test
	public void testGetAllServices() {
		List<ServiceType> serviceTypes = new ArrayList<>();
		serviceTypes.add(new ServiceType(1, "Oil Change", LocalTime.of(0,20)));
		serviceTypes.add(new ServiceType(2, "Brake Service", LocalTime.of(0,30)));
		when(carServices.getAllServices()).thenReturn(serviceTypes);

		List<ServiceType> result = carServices.getAllServices();

		assertEquals(serviceTypes.size(), result.size());
		verify(carServices, times(1)).getAllServices();
	}

	@Test
	public void testAddService() {
		List<ServiceType> serviceTypes = new ArrayList<>();
		serviceTypes.add(new ServiceType(1, "Oil Change", LocalTime.of(0,25)));
		serviceTypes.add(new ServiceType(2, "Brake Service", LocalTime.of(0,35)));
		when(carServices.addService(serviceTypes)).thenReturn(serviceTypes);

		List<ServiceType> result = carServices.addService(serviceTypes);

		assertEquals(serviceTypes.size(), result.size());
		verify(carServices, times(1)).addService(serviceTypes);
	}

	@Test
	public void testGetBikeService() {
		int serviceId = 1;
		ServiceType serviceType = new ServiceType(serviceId, "Oil Change", LocalTime.of(0,50));
		when(bikeServices.getService(serviceId)).thenReturn(serviceType);

		ServiceType result = bikeServices.getService(serviceId);

		assertEquals(serviceType, result);
		verify(bikeServices, times(1)).getService(serviceId);
	}

	@Test
	public void testGetAllBikeServices() {
		List<ServiceType> serviceTypes = new ArrayList<>();
		serviceTypes.add(new ServiceType(1, "Oil Change", LocalTime.of(0,20)));
		serviceTypes.add(new ServiceType(2, "Brake Service", LocalTime.of(0,30)));
		when(bikeServices.getAllServices()).thenReturn(serviceTypes);

		List<ServiceType> result = bikeServices.getAllServices();

		assertEquals(serviceTypes.size(), result.size());
		verify(bikeServices, times(1)).getAllServices();
	}

	@Test
	public void testAddBikeService() {
		List<ServiceType> serviceTypes = new ArrayList<>();
		serviceTypes.add(new ServiceType(1, "Oil Change", LocalTime.of(0,25)));
		serviceTypes.add(new ServiceType(2, "Brake Service", LocalTime.of(0,35)));
		when(bikeServices.addService(serviceTypes)).thenReturn(serviceTypes);

		List<ServiceType> result = bikeServices.addService(serviceTypes);

		assertEquals(serviceTypes.size(), result.size());
		verify(bikeServices, times(1)).addService(serviceTypes);
	}

}
