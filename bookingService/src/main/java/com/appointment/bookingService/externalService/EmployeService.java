package com.appointment.bookingService.externalService;


import com.appointment.bookingService.model.Employee;
import com.appointment.bookingService.model.TimeSlot;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@FeignClient(name = "Employee-Service", url = "http://localhost:8084/Vehicle-Service/employee")
public interface EmployeService {
    @GetMapping("/{employeeId}")
    Employee getEmployee(int employeeId);

    @GetMapping("/employees")
    List<Employee> getAllEmployee();

    @PostMapping("/add")
    List<Employee> addEmployee(List<Employee> employees);

    @GetMapping("/availibility/{employeeId}")
    Map<DayOfWeek, TimeSlot> getAllAvailableSlotsOfEmployee(int employeeId);

    @PostMapping("/availibility/availableEmployees")
    List<Employee> getAllAvailableEmployeeByTime(TimeSlot timeSlot);
}
