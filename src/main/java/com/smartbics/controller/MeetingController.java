package com.smartbics.controller;

import com.smartbics.model.Booking;
import com.smartbics.model.Meeting;
import com.smartbics.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MeetingController {

	@Autowired
	private MeetingService meetingService;

	@PostMapping(value = "/reserve", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Meeting> booking(@RequestBody List<Booking> bookings) {
		List<Meeting> meetings = meetingService.getMeetingsOfBookings(bookings);
//		System.out.println(meetings);
		return meetings;
	}

	@GetMapping(value = "/meeting")
	public Meeting getMeetingByDate(@RequestParam(name = "date", required = false) LocalDate localDate) {
		return meetingService.getMeetingByDate(localDate);
	}

	@GetMapping(value = "/meetings")
	public List<Meeting> getAllMeetings() {
		return meetingService.getAllMeetings();
	}

}
