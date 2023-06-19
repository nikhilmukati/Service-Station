package com.appointment.bookingService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private int employeeId;
    private String name;
    private Map<DayOfWeek, TimeSlot> availableTime;
    private Map<DayOfWeek, TimeSlot> bookedTime;
}
