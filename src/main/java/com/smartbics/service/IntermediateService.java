package com.smartbics.service;

import com.smartbics.model.Booking;
import com.smartbics.model.MeetingReq;
import com.smartbics.model.intermediate.BookingIntermediate;
import com.smartbics.model.intermediate.MeetingIntemediate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class IntermediateService {

	public Booking createBoking(BookingIntermediate data) {
		Booking booking = new Booking();
		booking.setStart(parse(data.getStart()));
		booking.setEnd(parse(data.getEnd()));
		List<MeetingReq> meetings = new ArrayList<>();
		for (MeetingIntemediate m : data.getBookingList()) {
			meetings.add(new MeetingReq(m.getBookingDate(), m.getEmployerName(), m.getMeetingDate(), m.getDuration()));
		}
		booking.setBookingList(meetings);
		return booking;
	}

	private LocalTime parse(String string) {
		int hour = Integer.parseInt(string.substring(0, 2));
		int minute = Integer.parseInt(string.substring(2, 4));
		return LocalTime.of(hour, minute);
	}
}
