package com.smartbics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/* аннотация @Data нужна для генерации геттеров и сеттеров.
   чтобы она работала нужно в Idea подключить Lombok Plugin
*/
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Booking booking = (Booking) o;

		if (duration != booking.duration) return false;
		if (bookingTime != null ? !bookingTime.equals(booking.bookingTime) : booking.bookingTime != null) return false;
		if (emp != null ? !emp.equals(booking.emp) : booking.emp != null) return false;
		return meetingTime != null ? meetingTime.equals(booking.meetingTime) : booking.meetingTime == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (bookingTime != null ? bookingTime.hashCode() : 0);
		result = 31 * result + (emp != null ? emp.hashCode() : 0);
		result = 31 * result + (meetingTime != null ? meetingTime.hashCode() : 0);
		result = 31 * result + duration;
		return result;
	}
}
