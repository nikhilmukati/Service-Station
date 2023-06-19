package com.employeeService.employee.services.interfaces;

import com.employeeService.employee.model.Employee;
import com.employeeService.employee.model.TimeSlot;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee getEmployee(int employeeId);

    List<Employee> getAllEmployee();

    List<Employee> addEmployee(List<Employee> employees);

    Map<DayOfWeek, TimeSlot> getAllAvailableSlotsOfEmployee(int employeeId);

    List<Employee> getAllAvailableEmployeeByTime(TimeSlot timeSlot);

    List<Employee> getAllAvailableEmployee();


}
