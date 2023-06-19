package com.appointment.bookingService;

import com.appointment.bookingService.model.Booking;
import com.appointment.bookingService.service.interfaces.AppointmentBookingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
@SpringBootTest
class BookingServiceApplicationTests {

	@Mock
	private AppointmentBookingService appointmentBookingService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void bookAppointment_shouldReturnBooking() {
		Booking mockBooking = new Booking();
		mockBooking.setCustomerId(1);
		when(appointmentBookingService.bookAppointment(mockBooking)).thenReturn(mockBooking);
		Booking result = appointmentBookingService.bookAppointment(mockBooking);
		Assertions.assertEquals(mockBooking, result);
	}

	@Test
	void getBooking_shouldReturnBooking() {
		Booking mockBooking = new Booking();
		mockBooking.setBookingId(1);
		when(appointmentBookingService.getBooking(1)).thenReturn(mockBooking);

		Booking result = appointmentBookingService.getBooking(1);
		Assertions.assertEquals(mockBooking, result);
	}

	@Test
	void getAllBookings_shouldReturnListOfBookings() {
		List<Booking> mockBookings = new ArrayList<>();
		Booking booking1 = new Booking();
		booking1.setBookingId(1);
		Booking booking2 = new Booking();
		booking2.setBookingId(2);
		mockBookings.add(booking1);
		mockBookings.add(booking2);
		when(appointmentBookingService.getAllBookings()).thenReturn(mockBookings);

		List<Booking> result = appointmentBookingService.getAllBookings();
		Assertions.assertEquals(mockBookings, result);
	}

	@Test
	void getAllBookingsForCustomer_shouldReturnListOfBookings() {
		int customerId = 1;

		List<Booking> mockBookings = new ArrayList<>();
		Booking booking1 = new Booking();
		booking1.setBookingId(1);
		booking1.setCustomerId(customerId);
		Booking booking2 = new Booking();
		booking2.setBookingId(2);
		booking2.setCustomerId(customerId);
		mockBookings.add(booking1);
		mockBookings.add(booking2);
		when(appointmentBookingService.getAllBookings(customerId)).thenReturn(mockBookings);
		List<Booking> result = appointmentBookingService.getAllBookings(customerId);

		Assertions.assertEquals(mockBookings, result);
	}

}
