package com.employeeService.employee;

import com.employeeService.employee.model.Employee;
import com.employeeService.employee.model.TimeSlot;
import com.employeeService.employee.services.interfaces.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceApplicationTests {

	@Mock
	private EmployeeService employeeService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetEmployeeWithValidEmployeeId() {
		int employeeId = 1;
		Employee employee = new Employee(1, "Joan", new HashMap<>(), new HashMap<>());
		when(employeeService.getEmployee(employeeId)).thenReturn(employee);

		Employee result = employeeService.getEmployee(employeeId);

		assertEquals(employee, result);
		verify(employeeService, times(1)).getEmployee(employeeId);
	}

	@Test
	public void testGetEmployeeWithInvalidEmployeeId() {
		int employeeId = 999; // Invalid employee ID
		when(employeeService.getEmployee(employeeId)).thenReturn(null);

		Employee result = employeeService.getEmployee(employeeId);

		assertNull(result);
		verify(employeeService, times(1)).getEmployee(employeeId);
	}

	@Test
	public void testGetAllEmployeeWithExistingEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(1, "Joan", new HashMap<>(), new HashMap<>()));
		employees.add(new Employee(2, "Joan", new HashMap<>(), new HashMap<>()));
		when(employeeService.getAllEmployee()).thenReturn(employees);

		List<Employee> result = employeeService.getAllEmployee();

		assertEquals(employees.size(), result.size());
		verify(employeeService, times(1)).getAllEmployee();
	}

	@Test
	public void testGetAllEmployeeWithNoEmployees() {
		when(employeeService.getAllEmployee()).thenReturn(new ArrayList<>());

		List<Employee> result = employeeService.getAllEmployee();

		assertTrue(result.isEmpty());
		verify(employeeService, times(1)).getAllEmployee();
	}

	@Test
	public void testAddEmployeeWithValidEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(1, "Joan", new HashMap<>(), new HashMap<>()));
		employees.add(new Employee(2, "Joan", new HashMap<>(), new HashMap<>()));
		when(employeeService.addEmployee(employees)).thenReturn(employees);

		List<Employee> result = employeeService.addEmployee(employees);

		assertEquals(employees.size(), result.size());
		verify(employeeService, times(1)).addEmployee(employees);
	}

	@Test
	public void testAddEmployeeWithNullList() {
		List<Employee> employees = null; // Null list

		List<Employee> result = employeeService.addEmployee(employees);

		assertTrue(result.isEmpty());
		verify(employeeService, never()).addEmployee(anyList());
	}

	@Test
	public void testGetAllAvailableSlotsOfEmployeeWithValidEmployeeId() {
		int employeeId = 1;
		Map<DayOfWeek, TimeSlot> availableSlots = new HashMap<>();
		availableSlots.put(DayOfWeek.MONDAY, new TimeSlot(LocalDateTime.of(LocalDate.now(), LocalTime.now()),LocalDateTime.of(LocalDate.now(), LocalTime.now().plusHours(1))));
		availableSlots.put(DayOfWeek.TUESDAY, new TimeSlot(LocalDateTime.of(LocalDate.now(), LocalTime.now()),LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.now().plusHours(1))));
		when(employeeService.getAllAvailableSlotsOfEmployee(employeeId)).thenReturn(availableSlots);

		Map<DayOfWeek, TimeSlot> result = employeeService.getAllAvailableSlotsOfEmployee(employeeId);

		assertEquals(availableSlots, result);
		verify(employeeService, times(1)).getAllAvailableSlotsOfEmployee(employeeId);
	}

	@Test
	public void testGetAllAvailableSlotsOfEmployeeWithInvalidEmployeeId() {
		int employeeId = 999; // Invalid employee ID
		when(employeeService.getAllAvailableSlotsOfEmployee(employeeId)).thenReturn(null);

		Map<DayOfWeek, TimeSlot> result = employeeService.getAllAvailableSlotsOfEmployee(employeeId);

		assertNull(result);
		verify(employeeService, times(1)).getAllAvailableSlotsOfEmployee(employeeId);
	}


}
