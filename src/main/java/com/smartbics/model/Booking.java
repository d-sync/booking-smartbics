package com.smartbics.model;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class Booking {

	private LocalTime start;

	private LocalTime end;

	private List<MeetingReq> bookingList;

	public Booking() {
	}

	@Override
	public String toString() {
		return "Booking{" +
				"start='" + start + '\'' +
				", end='" + end + '\'' +
				", bookingList=" + bookingList +
				'}';
	}
}
