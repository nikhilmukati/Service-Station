package com.appointment.bookingService.service.implementations;

import com.appointment.bookingService.customExceptions.BookingAlradyExistException;
import com.appointment.bookingService.customExceptions.NoAvailableSlotFound;
import com.appointment.bookingService.customExceptions.NoBookingExistException;
import com.appointment.bookingService.customExceptions.VehicleValidationFailed;
import com.appointment.bookingService.externalService.EmployeService;
import com.appointment.bookingService.externalService.ServiceTypeService;
import com.appointment.bookingService.externalService.VehicleService;
import com.appointment.bookingService.model.*;
import com.appointment.bookingService.service.interfaces.AppointmentBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentBookingServiceImpl implements AppointmentBookingService {

    private Map<Integer, Booking> bookings = new LinkedHashMap<>();

    @Autowired
    private EmployeService employeService;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private VehicleService vehicleService;

    @Override
    public Booking bookAppointment(Booking booking) {
        booking.setBookingId(bookings.size()+1);
        if (validBookingRequest(booking)) {
            LocalDateTime startDateTime = LocalDateTime.of(booking.getAppointmentDate(), booking.getAppointmentTime());
            Map<DayOfWeek, TimeSlot> availableTime = calculateBookingTime(booking);
            LocalDateTime endDateTime = LocalDateTime.of(booking.getAppointmentEndDate(), booking.getAppointmentEndTime());
            TimeSlot timeSlot = new TimeSlot(startDateTime, endDateTime);

            getAvilableEmployee(startDateTime, availableTime, timeSlot);
            Vehicle vehicle = vehicleService.acceptVehicleForService(booking.getVehicleId());
            if(vehicle.getAccepted()){
                bookings.put(booking.getBookingId(),booking);
            }else{
                throw new VehicleValidationFailed("Vehicle Details Verification failed!");
            }
        }
        return booking;
    }

    private void getAvilableEmployee(LocalDateTime startDateTime, Map<DayOfWeek, TimeSlot> availableTime, TimeSlot timeSlot) {
        List<Employee> employees = getAvailableEmployee(timeSlot);
        Optional.ofNullable(employees).orElseThrow(RuntimeException::new);
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                Map<DayOfWeek, TimeSlot> availableTime1 = employee1.getAvailableTime();
                Map<DayOfWeek, TimeSlot> availableTime2 = employee2.getAvailableTime();
                DayOfWeek day = LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.SUNDAY) ?
                        LocalDate.now().plusDays(1).getDayOfWeek() : LocalDate.now().getDayOfWeek();

                LocalTime startTime1 = LocalTime.from(availableTime1.get(day).getStartDateTime());
                LocalTime startTime2 = LocalTime.from(availableTime2.get(day).getStartDateTime());
                return startTime1.compareTo(startTime2);
            }
        });

        Employee employee = employees.stream().findFirst().orElseThrow(()-> new NoAvailableSlotFound("No Available Slot Found!"));
        timeSlot.setStartDateTime(employee.getAvailableTime().get(startDateTime.getDayOfWeek()).getStartDateTime());

        employee.setBookedTime(Map.of(startDateTime.getDayOfWeek(), timeSlot));

        employee.setAvailableTime(availableTime);

        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        employeService.addEmployee(employeeList);
    }

    private Map<DayOfWeek, TimeSlot> calculateBookingTime(Booking booking) {
        List<String> requiredServices = booking.getRequiredServices();
        List<ServiceType> allServiceTypes;
        allServiceTypes = getServiceTypeList(booking);
        LocalTime requiredTotalTime = getRequiredTotalTime(booking, requiredServices, allServiceTypes);

        booking.setBookedTime(
                requiredTotalTime.minusHours(booking.getAppointmentTime().getHour()).minusMinutes(booking.getAppointmentTime().getMinute())
        );

        Map<DayOfWeek, TimeSlot> availableTime = new HashMap<>();

        TimeSlot availableTimeSlot = new TimeSlot(
                LocalDateTime.now(),
                LocalDateTime.of(booking.getAppointmentDate(), LocalTime.of(18, 0))
        );

        if (requiredTotalTime.isBefore(LocalTime.of(17, 0))) {
            booking.setAppointmentEndTime(requiredTotalTime);
            booking.setAppointmentEndDate(booking.getAppointmentDate());
            availableTimeSlot.setStartDateTime(
                    LocalDateTime.of(booking.getAppointmentEndDate(), booking.getAppointmentEndTime())
            );
            availableTime.put(booking.getAppointmentDate().getDayOfWeek(), availableTimeSlot);
        } else {
            calculateFutureTimeRequired(booking, requiredTotalTime);

            if (DayOfWeek.SUNDAY.equals(booking.getAppointmentDate().plusDays(1).getDayOfWeek())) {
                booking.setAppointmentEndDate(booking.getAppointmentDate().plusDays(2));
            } else {
                booking.setAppointmentEndDate(booking.getAppointmentDate().plusDays(1));
            }
            updateAvailableTimeEntries(booking, availableTime, availableTimeSlot);
        }
        return  availableTime;
    }

    private static void updateAvailableTimeEntries(Booking booking, Map<DayOfWeek, TimeSlot> availableTime, TimeSlot availableTimeSlot) {
        availableTimeSlot.setStartDateTime(
                LocalDateTime.of(booking.getAppointmentEndDate(), booking.getAppointmentEndTime())
        );

        availableTime.put(booking.getAppointmentDate().getDayOfWeek(),
                new TimeSlot(
                        LocalDateTime.of(booking.getAppointmentDate(), LocalTime.of(18, 0)),
                        LocalDateTime.of(booking.getAppointmentDate(), LocalTime.of(18, 0))
                )
        );

        availableTime.put(booking.getAppointmentEndDate().getDayOfWeek(),
            new TimeSlot(
                    LocalDateTime.of(booking.getAppointmentEndDate(), booking.getAppointmentEndTime()),
                    LocalDateTime.of(booking.getAppointmentEndDate(), LocalTime.of(18, 0))
            )
        );
    }

    private static void calculateFutureTimeRequired(Booking booking, LocalTime requiredTotalTime) {
        LocalTime requiredNextDayTime = requiredTotalTime.minusHours(17).minusMinutes(0);
/*        LocalTime requiredSameDayTime = LocalTime.of(17, 0)
                .minusHours(booking.getAppointmentTime().getHour())
                .minusMinutes(booking.getAppointmentTime().getMinute());
*/
        LocalTime nextDayEndTime = LocalTime.of(9, 0).plusHours(requiredNextDayTime.getHour())
                .plusMinutes(requiredNextDayTime.getMinute());
        booking.setAppointmentEndTime(nextDayEndTime);
    }

    private static LocalTime getRequiredTotalTime(Booking booking, List<String> requiredServices, List<ServiceType> allServiceTypes) {
        int requiredTime = allServiceTypes.stream()
                .filter(service -> requiredServices.contains(service.getServiceType()))
                .map(ServiceType::getTimeRequired)
                .map(LocalTime::getMinute)
                .reduce(Integer::sum).get();

        LocalTime requiredTotalTime = booking.getAppointmentTime();
        requiredTotalTime = requiredTotalTime.plusHours(requiredTime / 60)
                .plusMinutes(requiredTime % 60);
        return requiredTotalTime;
    }

    private List<ServiceType> getServiceTypeList(Booking booking) {
        List<ServiceType> allServiceTypes;
        if ("bike".equalsIgnoreCase(booking.getVehicleType())) {
            allServiceTypes = serviceTypeService.getAllBikeServices();
        } else {
            allServiceTypes = serviceTypeService.getAllCarServices();
        }
        return allServiceTypes;
    }

    @Override
    public Booking getBooking(int bookingId) {
        if (bookings.containsKey(bookingId))
            return bookings.get(bookingId);
        throw new NoBookingExistException("Booking not Found Whit this ID!");
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookings.values().stream().toList();
    }

    @Override
    public List<Booking> getAllBookings(int customerId) {
        return bookings.values().stream().filter(book -> book.getCustomerId()==customerId).collect(Collectors.toList());
    }

    public List<Employee> getAvailableEmployee(TimeSlot dateTime) {
        return employeService.getAllAvailableEmployeeByTime(dateTime);
    }


    public boolean validBookingRequest(Booking booking) {
        if (Objects.nonNull(booking)) {
            if(bookings.values().stream().anyMatch(book -> book.getVehicleId()==booking.getVehicleId())){
                throw  new BookingAlradyExistException("Booking already Exist!");
            }
            if (booking.getBookingId() <= 0 || booking.getCustomerId() <= 0 || booking.getVehicleId() <= 0) {
                return false; // if Invalid bookingId, customerId, or vehicleId
            }

            LocalDate currentDate = LocalDate.now();
            if (booking.getAppointmentDate().isBefore(currentDate)) {
                return false; // if Appointment date is in the past
            }

            LocalTime currentTime = LocalTime.now();
            if (booking.getAppointmentDate().equals(currentDate) && booking.getAppointmentTime().isBefore(currentTime)) {
                throw new BookingAlradyExistException("Booking in Past Not Allowed!"); // if Appointment time is in the past
            }
        } else {
            return false;
        }
        return true;
    }
}
