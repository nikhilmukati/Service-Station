package com.employeeService.employee.controller;

import com.employeeService.employee.model.Employee;
import com.employeeService.employee.model.TimeSlot;
import com.employeeService.employee.services.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Vehicle-Service/employee")
public class employeeServiceController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId){
        return new ResponseEntity<>(employeeService.getEmployee(employeeId), HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<List<Employee>> addEmployee(@RequestBody  List<Employee> employees){
        Optional.ofNullable(employees)
                .ifPresentOrElse((employee) ->
                        employeeService.addEmployee(employee),
                        () -> { throw  new RuntimeException();}
                );
        return new ResponseEntity<>(employees, HttpStatus.CREATED);
    }

    @GetMapping("/availibility/{employeeId}")
    public ResponseEntity<Map<DayOfWeek, TimeSlot>> getAllAvailableSlotsOfEmployee(@PathVariable int employeeId){
        return new ResponseEntity<>(employeeService.getAllAvailableSlotsOfEmployee(employeeId), HttpStatus.OK);
    }

    @GetMapping("/availibility/availableEmployees")
    public ResponseEntity<List<Employee>> getAllAvailableEmployee(){
        return new ResponseEntity<>(employeeService.getAllAvailableEmployee(), HttpStatus.OK);
    }

    @PostMapping("/availibility/availableEmployees")
    public ResponseEntity<List<Employee>> getAllAvailableEmployee(@RequestBody TimeSlot timeSlot){
        return new ResponseEntity<>(employeeService.getAllAvailableEmployeeByTime(timeSlot), HttpStatus.OK);
    }
}
