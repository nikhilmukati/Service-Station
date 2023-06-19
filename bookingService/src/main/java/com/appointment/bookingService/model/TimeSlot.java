package com.appointment.bookingService.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeSlot {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public TimeSlot(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
