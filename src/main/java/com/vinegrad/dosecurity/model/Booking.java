package com.vinegrad.dosecurity.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@ToString
public class Booking {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long bookingId;
	@Column(name="date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@NotEmpty(message="{date.notempty}")
	private LocalDate date;
	@Column(name="time")
	@DateTimeFormat(pattern = "HH:mm")
//	@NotEmpty(message="{time.notempty}")
	private String time;
	@Column(name="number_of_people")
//	@Range(min=1,max=10,c="{numberOfPeople.range}")
	private int numberOfPeople;
	@Column(name="special_requirements")
	private String specialRequirements;
	@Column(name="booking_user")
	private String bookingUser;
	
	public Booking(LocalDate date, String time, int numberOfPeople, String specialRequirements, String bookingUser) {
		super();
		this.date = date;
		this.time = time;
		this.numberOfPeople = numberOfPeople;
		this.specialRequirements = specialRequirements;
		this.bookingUser = bookingUser;
	}
	
	
}
