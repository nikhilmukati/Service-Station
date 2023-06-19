package com.serviceType.services;

import com.serviceType.services.models.ServiceType;
import com.serviceType.services.service.implementations.BikeServicesImpl;
import com.serviceType.services.service.implementations.CarServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ServiceTypeApplicationNeTests {

    @Mock
    private BikeServicesImpl bikeServices;

    @Mock
    private CarServicesImpl carServices;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetServiceWithInvalidServiceId() {
        int serviceId = 999; // Invalid service ID
        when(carServices.getService(serviceId)).thenReturn(null);

        ServiceType result = carServices.getService(serviceId);

        assertNull(result);
        verify(carServices, times(1)).getService(serviceId);
    }

    @Test
    public void testGetAllServicesWithEmptyList() {
        when(carServices.getAllServices()).thenReturn(new ArrayList<>());

        List<ServiceType> result = carServices.getAllServices();

        assertTrue(result.isEmpty());
        verify(carServices, times(1)).getAllServices();
    }

    @Test
    public void testAddServiceWithNullList() {
        List<ServiceType> serviceTypes = null;

        List<ServiceType> result = carServices.addService(null);

        assertTrue(result.isEmpty());
        verify(carServices, never()).addService(anyList());
    }

    @Test
    public void testAddServiceWithEmptyList() {
        List<ServiceType> serviceTypes = new ArrayList<>();

        List<ServiceType> result = carServices.addService(serviceTypes);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetBikeServiceWithInvalidServiceId() {
        int serviceId = 999; // Invalid service ID
        when(bikeServices.getService(serviceId)).thenReturn(null);

        ServiceType result = bikeServices.getService(serviceId);

        assertNull(result);
        verify(bikeServices, times(1)).getService(serviceId);
    }

    @Test
    public void testGetAllBikeServicesWithEmptyList() {
        when(bikeServices.getAllServices()).thenReturn(new ArrayList<>());

        List<ServiceType> result = bikeServices.getAllServices();

        assertTrue(result.isEmpty());
        verify(bikeServices, times(1)).getAllServices();
    }

    @Test
    public void testAddBikeServiceWithNullList() {
        List<ServiceType> serviceTypes = null;

        List<ServiceType> result = bikeServices.addService(null);

        assertTrue(result.isEmpty());
        verify(bikeServices, never()).addService(anyList());
    }

    @Test
    public void testAddBikeServiceWithEmptyList() {
        List<ServiceType> serviceTypes = new ArrayList<>();

        List<ServiceType> result = bikeServices.addService(serviceTypes);

        assertTrue(result.isEmpty());
    }
}
