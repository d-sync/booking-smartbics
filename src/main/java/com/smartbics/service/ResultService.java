package com.smartbics.service;

import com.smartbics.model.Booking;
import com.smartbics.model.MeetigResp;
import com.smartbics.model.MeetingReq;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class ResultService {


	public List<MeetigResp> getMeetingResp(Booking booking) {
		LocalTime jobStart = booking.getStart();
		LocalTime jobEnd = booking.getEnd();
		List<MeetingReq> reqs = booking.getBookingList();




		return null;
	}
}
