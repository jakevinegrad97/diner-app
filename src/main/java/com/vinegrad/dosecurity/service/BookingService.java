package com.vinegrad.dosecurity.service;

import java.util.List;
import java.util.Optional;

import com.vinegrad.dosecurity.model.Booking;

public interface BookingService {

	boolean addBooking(Booking booking);

	List<Booking> findBookingsByUsername(String username);

	boolean deleteBooking(Long bookingId);

	boolean updateBooking(Booking oldBooking, Booking newBooking);

	Optional<Booking> findBookingById(Long bookingId);

}