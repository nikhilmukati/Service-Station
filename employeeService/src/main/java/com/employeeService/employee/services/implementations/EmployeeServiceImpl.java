package com.employeeService.employee.services.implementations;

import com.employeeService.employee.model.Employee;
import com.employeeService.employee.model.TimeSlot;
import com.employeeService.employee.services.interfaces.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Map<Integer, Employee> employeeMap = new HashMap<>();

    {
        employeeMap.put(1, new Employee(1, "Joan", getWeekDataObject(),
                new HashMap<>()
        ));
        employeeMap.put(2, new Employee(2, "Jani", getWeekDataObject(),
                new HashMap<>()
        ));
    }

    @Override
    public Employee getEmployee(int employeeId) {
        return employeeMap.get(employeeId);
    }

    @Override
    public List<Employee> getAllEmployee() {//LocalDateTime.now().getDayOfWeek()
        return employeeMap.values().stream().toList();
    }

    @Override
    public List<Employee> addEmployee(List<Employee> employees) {
        Optional.ofNullable(employees)
                .ifPresentOrElse( (employee) ->
                        employee.forEach(emp -> employeeMap.put(emp.getEmployeeId(), emp)),
                        () -> { throw  new RuntimeException(); }
                );
        return employees;
    }

    @Override
    public Map<DayOfWeek, TimeSlot> getAllAvailableSlotsOfEmployee(int employeeId) {
        LinkedHashMap<DayOfWeek, TimeSlot> slotLinkedHashMap = new LinkedHashMap<>();
         employeeMap.get(employeeId).getAvailableTime()
                .entrySet()
                .stream()
                .filter(entry ->
                                entry.getValue().getStartDateTime().isAfter(LocalDateTime.now()) &&
                                entry.getValue().getEndDateTime().isAfter(LocalDateTime.now())
                ).forEach(slot -> slotLinkedHashMap.put(slot.getKey(), slot.getValue()));
         return slotLinkedHashMap;
    }

    @Override
    public List<Employee> getAllAvailableEmployeeByTime(TimeSlot timeSlot) {

       return employeeMap.values().stream()
                .filter(employee -> employee.getAvailableTime()
                        .values()
                        .stream()
                        .anyMatch(
                                timeSlots -> timeSlot.getStartDateTime().isAfter(timeSlots.getStartDateTime()) ||
                                        timeSlot.getStartDateTime().isEqual(timeSlots.getStartDateTime())
                        )
                ).collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAllAvailableEmployee() {
        LocalDateTime currDateTime = DayOfWeek.SUNDAY.equals(LocalDateTime.now().getDayOfWeek())?
                                                LocalDateTime.now().plusDays(1):LocalDateTime.now();
        return employeeMap.values().stream()
                .filter(employee -> employee.getAvailableTime()
                        .values()
                        .stream()
                        .anyMatch(
                                timeSlots -> currDateTime.isAfter(timeSlots.getStartDateTime()) ||
                                        currDateTime.isEqual(timeSlots.getStartDateTime())
                        )
                ).collect(Collectors.toList());
    }

    public Map<DayOfWeek, TimeSlot> getWeekDataObject(){
        Map<DayOfWeek, TimeSlot> weekTimeSlotHashMap =
                new LinkedHashMap<>();
        for(int day=0;day<2;day++) {
            if (DayOfWeek.SUNDAY.equals(LocalDate.now().getDayOfWeek().plus(day)))
                continue;
            weekTimeSlotHashMap.put(LocalDate.now().getDayOfWeek().plus(day), new TimeSlot(
                    LocalDateTime.of(LocalDate.now().plusDays(day), LocalTime.of(9, 0)),
                    LocalDateTime.of(LocalDate.now().plusDays(day), LocalTime.of(18, 0))
            ));
        }
        return weekTimeSlotHashMap;
    }

}
