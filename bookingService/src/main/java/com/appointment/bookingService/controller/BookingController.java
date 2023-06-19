package com.appointment.bookingService.controller;

import com.appointment.bookingService.model.Booking;
import com.appointment.bookingService.service.interfaces.AppointmentBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Vehicle-Service/booking")
public class BookingController {

    @Autowired
    private AppointmentBookingService bookingService;

    @PostMapping("/add")
    public ResponseEntity<Booking> bookAppointment(@RequestBody  Booking booking){
        bookingService.bookAppointment(booking);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @GetMapping("/get/{bookingId}")
    public ResponseEntity<Booking> getBooking(@PathVariable int bookingId){
        return new ResponseEntity<>(bookingService.getBooking(bookingId), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Booking>> getAllBookings(){
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Booking>> getBookings(@PathVariable int customerId){
        return new ResponseEntity<>(bookingService.getAllBookings(customerId), HttpStatus.OK);
    }

}
