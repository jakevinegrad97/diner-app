package com.vinegrad.dosecurity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinegrad.dosecurity.model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
	List<Booking> findByBookingUserIn(String username);
}
