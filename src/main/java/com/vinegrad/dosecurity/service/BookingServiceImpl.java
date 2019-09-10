package com.vinegrad.dosecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinegrad.dosecurity.model.Booking;
import com.vinegrad.dosecurity.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository repository;
	
	@Override
	public boolean addBooking(Booking booking) {
		repository.save(booking);
		return true;
	}
	
	@Override
	public List<Booking> findBookingsByUsername(String username){
		return repository.findByBookingUserIn(username);
	}
	
	@Override
	public boolean deleteBooking(Long bookingId) {
		repository.deleteById(bookingId);
		return true;
	}
	
	@Override
	public boolean updateBooking(Booking oldBooking, Booking newBooking) {
		repository.delete(oldBooking);
		repository.save(newBooking);
		return true;
	}
	
	@Override
	public Optional<Booking> findBookingById(Long bookingId){
		Booking result = repository.findById(bookingId).get();
		return Optional.ofNullable(result);
	}
	
}
