package com.smartbics.model.intermediate;

import lombok.Data;

import java.util.List;

@Data
public class BookingIntermediate {
	private String start;

	private String end;

	private List<MeetingIntemediate> bookingList;

	public BookingIntermediate() {
	}
}
