package com.smartbics.controller;

import com.smartbics.model.Booking;
import com.smartbics.model.Meeting;
import com.smartbics.model.MeetingInformation;
import com.smartbics.service.MeetingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = MeetingController.class, secure = false)
public class MeetingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MeetingService meetingService;

	@Test
	public void getAllMeetingsTest() throws Exception {
		List<Meeting> meetings = new ArrayList<>();
		MeetingInformation mi1 = new MeetingInformation(LocalTime.of(9,0), LocalTime.of(11, 0), "EMP001");
		MeetingInformation mi2 = new MeetingInformation(LocalTime.of(12,0), LocalTime.of(14, 0), "EMP002");
		Meeting meeting1 = new Meeting(LocalDate.of(2017, 10, 10), new ArrayList<>(Arrays.asList(mi1, mi2)));
		MeetingInformation mi3 = new MeetingInformation(LocalTime.of(14,0), LocalTime.of(15, 0), "EMP003");
		Meeting meeting2 = new Meeting(LocalDate.of(2017, 10, 11), new ArrayList<>(Arrays.asList(mi3)));
		meetings.add(meeting1);
		meetings.add(meeting2);

		when(meetingService.getAllMeetings()).thenReturn(meetings);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api//meetings");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"date\":\"2017-10-10\",\"info\":[{\"startMeeting\":\"09:00\",\"endMeeting\":\"11:00\",\"emp\":\"EMP001\"},{\"startMeeting\":\"12:00\",\"endMeeting\":\"14:00\",\"emp\":\"EMP002\"}]},{\"date\":\"2017-10-11\",\"info\":[{\"startMeeting\":\"14:00\",\"endMeeting\":\"15:00\",\"emp\":\"EMP003\"}]}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void getMeetingByDateTest() throws Exception {
		MeetingInformation mi = new MeetingInformation(LocalTime.of(14,0), LocalTime.of(15, 0), "EMP003");
		Meeting meeting = new Meeting(LocalDate.of(2017, 10, 11), new ArrayList<>(Arrays.asList(mi)));

		when(meetingService.getMeetingByDate(LocalDate.of(2017, 10, 11))).thenReturn(meeting);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api//meeting?date=2017-10-11")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"date\":\"2017-10-11\",\"info\":[{\"startMeeting\":\"14:00\",\"endMeeting\":\"15:00\",\"emp\":\"EMP003\"}]}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

//	@Test
//	public void bookingTest() throws Exception {
//		Booking booking1 = new Booking(of(2011, 3, 17, 10, 17,6), "EMP001", of(2011, 3,21, 9, 0), 2);
//		Booking booking2 = new Booking(of(2011, 3, 16, 12, 34, 56), "EMP002", of(2011, 3, 21, 9,0), 2);
//		Booking booking3 = new Booking(of(2011, 3, 16, 9, 28, 23), "EMP003", of(2011, 3, 22, 14, 0), 2);
//		Booking booking4 = new Booking(of(2011, 3, 17, 11, 23, 45), "EMP004", of(2011, 3,22,16,0), 1);
//		Booking booking5 = new Booking(of(2011, 3,15,17,29,12), "EMP005", of(2011, 3, 21, 16, 0), 3);
//		List<Booking> bookings = new ArrayList<>(Arrays.asList(booking1, booking2, booking3, booking4, booking5));
//		MeetingInformation mi1 = new MeetingInformation(LocalTime.of(9,0), LocalTime.of(11, 0), "EMP002");
//		Meeting meeting1 = new Meeting(LocalDate.of(2011, 3, 21), new ArrayList<>(Arrays.asList(mi1)));
//		MeetingInformation mi2 = new MeetingInformation(LocalTime.of(14,0), LocalTime.of(16, 0), "EMP003");
//		MeetingInformation mi3 = new MeetingInformation(LocalTime.of(16,0), LocalTime.of(17, 0), "EMP004");
//		Meeting meeting2 = new Meeting(LocalDate.of(2011, 3, 22), new ArrayList<>(Arrays.asList(mi2, mi3)));
//		List<Meeting> meetings = new ArrayList<>(Arrays.asList(meeting1, meeting2));
//
//		when(meetingService.getMeetingsOfBookings(bookings)).thenReturn(meetings);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post("/api/reserve")
//				.accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		String expected = "[{\"date\":\"2011-03-21\",\"info\":[{\"startMeeting\":\"09:00\",\"endMeeting\":\"11:00\",\"emp\":\"EMP002\"}]},{\"date\":\"2011-03-22\",\"info\":[{\"startMeeting\":\"14:00\",\"endMeeting\":\"16:00\",\"emp\":\"EMP003\"},{\"startMeeting\":\"16:00\",\"endMeeting\":\"17:00\",\"emp\":\"EMP004\"}]}]";
//		JSONAssert.assertEquals(expected,
//				result.getResponse().getContentAsString(), false);
//	}

}
