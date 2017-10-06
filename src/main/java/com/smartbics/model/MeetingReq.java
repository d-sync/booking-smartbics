package com.smartbics.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingReq {

	private LocalDateTime bookingDate;

	private String employerName;

	private LocalDateTime meetingDate;

	private Integer duration;

	public MeetingReq() {
	}

	public MeetingReq(String bookingDate, String employerName, String meetingDate, Integer duration) {
		this.bookingDate = parseBookingDate(bookingDate);
		this.employerName = employerName;
		this.meetingDate = parseMeetingDate(meetingDate);
		this.duration = duration;
	}

	private LocalDateTime parseMeetingDate(String meetingDate) {
		int year = Integer.parseInt(meetingDate.substring(0, 4));
		int month = Integer.parseInt(meetingDate.substring(5, 7));
		int day = Integer.parseInt(meetingDate.substring(8, 10));
		int hour = Integer.parseInt(meetingDate.substring(11, 13));
		int minutes = Integer.parseInt(meetingDate.substring(14, 16));
		return LocalDateTime.of(year, month, day, hour, minutes);
	}

	private LocalDateTime parseBookingDate(String bookingDate) {
		int year = Integer.parseInt(bookingDate.substring(0, 4));
		int month = Integer.parseInt(bookingDate.substring(5, 7));
		int day = Integer.parseInt(bookingDate.substring(8,10));
		int hour = Integer.parseInt(bookingDate.substring(11, 13));
		int minutes = Integer.parseInt(bookingDate.substring(14, 16));
		int seconds = Integer.parseInt(bookingDate.substring(17, 19));
		return LocalDateTime.of(year, month, day, hour, minutes, seconds);

	}

	@Override
	public String toString() {
		return "MeetingReq{" +
				"bookingDate=" + bookingDate +
				", employerName='" + employerName + '\'' +
				", meetingDate=" + meetingDate +
				", duration=" + duration +
				'}';
	}

}
