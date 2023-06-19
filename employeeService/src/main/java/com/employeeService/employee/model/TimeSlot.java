package com.employeeService.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeSlot {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

}
