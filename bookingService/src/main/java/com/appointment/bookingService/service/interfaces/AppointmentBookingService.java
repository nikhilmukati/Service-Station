package com.appointment.bookingService.service.interfaces;

import com.appointment.bookingService.model.Booking;

import java.util.List;

public interface AppointmentBookingService {

    Booking bookAppointment(Booking booking);

    Booking getBooking(int bookingId);

    List<Booking> getAllBookings();

    List<Booking> getAllBookings(int customerId);

}
