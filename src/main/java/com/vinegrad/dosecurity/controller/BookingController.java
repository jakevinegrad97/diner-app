package com.vinegrad.dosecurity.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vinegrad.dosecurity.model.Booking;
import com.vinegrad.dosecurity.service.BookingService;

@Controller
@SessionAttributes({ "booking", "bookings", "bookingUpdate", "dateError", "timeError" , "numberError"})
public class BookingController {

	@Autowired
	private BookingService service;

	@RequestMapping(value = "/bookATable", method = RequestMethod.GET)
	public String bookTable(@ModelAttribute("bookingForm") Booking booking, Model model, BindingResult result) {
		model.addAttribute("dateError", "");
		model.addAttribute("timeError", "");
		model.addAttribute("numberError", "");
		return "bookTable";
	}

	@RequestMapping(value = "/bookATable", method = RequestMethod.POST)
	public String bookATable(@ModelAttribute("bookingForm") @Valid Booking booking, Model model, BindingResult result) {
		if (!result.hasErrors()) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
			String dateError = "";
			String timeError = "";
			String numberError = "";
			if (booking.getDate().isBefore(LocalDate.now())) {
				dateError = "Please book a date in the future!";
				model.addAttribute("dateError", dateError);
				return "bookTable";
			}
			if (Integer.valueOf(booking.getTime().substring(0, 2)) < 11
					| Integer.valueOf(booking.getTime().substring(0, 2)) > 23) {
				timeError = "We are open from 11:00 - 23:00!";
				model.addAttribute("timeError", timeError);
				return "bookTable";
			}
			if (booking.getNumberOfPeople() <= 0) {
				numberError = "Please enter a valid number of people";
				model.addAttribute("numberError", numberError);
				return "bookTable";
			}
			booking = new Booking(booking.getDate(), booking.getTime(), booking.getNumberOfPeople(),
					booking.getSpecialRequirements(), username);
			service.addBooking(booking);
			model.addAttribute("booking", booking);
			return "bookingSuccessful";

		} else {
			return "bookTable";
		}
	}

	@RequestMapping(value = "/allBookings", method = RequestMethod.GET)
	public String findBookings(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		List<Booking> bookings = service.findBookingsByUsername(username);
		model.addAttribute("bookings", bookings);
		return "myBookings";
	}

	@RequestMapping(value = "/delete/{bookingId}")
	public String deleteBooking(@PathVariable Long bookingId) {
		Booking booking = service.findBookingById(bookingId).get();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (username.equals(booking.getBookingUser())) {
			service.deleteBooking(bookingId);
			return "deleteDone";
		} else {
			return "error403";
		}
	}

	@RequestMapping(value = "/update/{bookingId}", method = RequestMethod.GET)
	public String updateBooking(@PathVariable Long bookingId, @ModelAttribute("updateBookingForm") Booking booking,
			BindingResult result, Model model) {
		Booking bookingUpdate = service.findBookingById(bookingId).get();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		model.addAttribute("dateError", "");
		model.addAttribute("timeError", "");
		model.addAttribute("numberError", "");
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (username.equals(bookingUpdate.getBookingUser())) {
			model.addAttribute("bookingUpdate", bookingUpdate);
			return "updateBooking";
		} else {
			return "error403";
		}
	}

	@RequestMapping(value = "/update/{bookingId}", method = RequestMethod.POST)
	public String updateATable(@PathVariable Long bookingId, @ModelAttribute("updateBookingForm") Booking booking,
			BindingResult result, Model model) {
		Booking oldBooking = service.findBookingById(bookingId).get();
		String dateError = "";
		String timeError = "";
		String numberError = "";
		if (booking.getDate().isBefore(LocalDate.now())) {
			dateError = "Please book a date in the future!";
			model.addAttribute("dateError", dateError);
			return "updateBooking";
		}
		if (Integer.valueOf(booking.getTime().substring(0, 2)) < 11 || Integer.valueOf(booking.getTime().substring(0, 2)) > 23) {
			timeError = "We are open from 11:00 - 23:00!";
			model.addAttribute("timeError", timeError);
			return "updateBooking";
		}
		if (booking.getNumberOfPeople() <= 0) {
			numberError = "Please enter a valid number of people";
			model.addAttribute("numberError", numberError);
			return "updateBooking";
		}
		service.updateBooking(oldBooking, new Booking(booking.getDate(), booking.getTime(), booking.getNumberOfPeople(),
				booking.getSpecialRequirements(), oldBooking.getBookingUser()));
		return "updateDone";

	}

}
