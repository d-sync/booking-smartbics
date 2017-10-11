package com.smartbics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Booking {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime bookingTime;

	private String emp;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime meetingTime;

	private int duration;

	@Override
	public String toString() {
		return "Booking{" +
				"bookingTime=" + bookingTime +
				", emp='" + emp + '\'' +
				", meetingTime=" + meetingTime +
				", duration=" + duration +
				'}';
	}

	public LocalDate getLocalDateOfMeetingTime() {
		return meetingTime.toLocalDate();
	}
}
