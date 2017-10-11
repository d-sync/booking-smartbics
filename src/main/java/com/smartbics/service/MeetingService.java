package com.smartbics.service;

import com.smartbics.model.Booking;

import com.smartbics.model.Meeting;
import com.smartbics.model.MeetingInformation;
import com.smartbics.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.*;

@Service
public class MeetingService {

	private static final LocalTime startWorkingTime = LocalTime.of(9, 0);
	private static final LocalTime endWorkingTime = LocalTime.of(17, 30);

	@Autowired
	private MeetingRepository meetingRepository;

	public List<Meeting> getMeetingsOfBookings(List<Booking> bookings) {
		Map<LocalDate, List<Booking>> map = bookings
				.stream()
				.filter(booking -> {
					LocalTime startMeeting = booking.getMeetingTime().toLocalTime();
					LocalTime endMeeting = startMeeting.plus(booking.getDuration(), HOURS);
					return startMeeting.isAfter(startWorkingTime.minus(1, NANOS)) & endMeeting.isBefore(endWorkingTime.plus(1, NANOS));
				})
				.collect(Collectors.groupingBy(Booking::getLocalDateOfMeetingTime));
		List<Meeting> meetings = new ArrayList<>();
		for (Map.Entry<LocalDate, List<Booking>> entry : map.entrySet()) {
			LocalDate date = entry.getKey();
			List<Booking> list = entry.getValue()
					.stream()
					.sorted(Comparator.comparing(Booking::getBookingTime))
					.collect(Collectors.toList());
			List<MeetingInformation> meetingInformations = new ArrayList<>();
			for (Booking b : list) {
				LocalTime startMeeting = b.getMeetingTime().toLocalTime();
				LocalTime endMeeting = startMeeting.plus(b.getDuration(), HOURS);
				if (startMeeting.isBefore(endMeeting) & !startMeeting.equals(endMeeting)) {
					MeetingInformation meetingInformation = new MeetingInformation(startMeeting, endMeeting, b.getEmp());
					Meeting alreadySaved = meetingRepository.getMeetingByDate(date);
					if (alreadySaved == null) {
						Meeting meeting = new Meeting(entry.getKey(), new ArrayList<>());
						meeting.addMeetingInformation(meetingInformation);
						meetingInformations.add(meetingInformation);
						meetingRepository.save(meeting);
					} else {
						boolean isCrossing = false;
						for (MeetingInformation m : alreadySaved.getInfo()) {
							if (!isCrossing) {
								if (endMeeting.isBefore(startMeeting)
										|| m.getStartMeeting().equals(startMeeting)
										|| (m.getStartMeeting().isBefore(startMeeting) & m.getEndMeeting().isAfter(startMeeting))
										|| (m.getStartMeeting().isAfter(startMeeting) & endMeeting.isAfter(m.getStartMeeting()))) {
									isCrossing = true;
								}
							}
						}
						if (!isCrossing) {
							alreadySaved.addMeetingInformation(meetingInformation);
							meetingInformations.add(meetingInformation);
							meetingRepository.save(alreadySaved);
						}
					}
				}
			}
			Meeting meeting = null;
			if (meetingInformations.size() > 0) {
				meetingInformations = meetingInformations.stream()
						.sorted(Comparator.comparing(MeetingInformation::getStartMeeting))
						.collect(Collectors.toList());
				meeting = new Meeting(entry.getKey(), meetingInformations);
				meetings.add(meeting);
			}
		}
		if (meetings.size() > 0) {
			meetings = meetings
					.stream()
					.sorted(Comparator.comparing(Meeting::getDate))
					.collect(Collectors.toList());
			return meetings;
		} else {
			return null;
		}

	}

	public Meeting getMeetingByDate(LocalDate date) {
		Meeting meeting = meetingRepository.getMeetingByDate(date);
		if (meeting != null) {
			meeting.setInfo(meeting.getInfo()
					.stream()
					.sorted(Comparator.comparing(MeetingInformation::getStartMeeting))
					.collect(Collectors.toList()));
		}
		return meeting;
	}

	public List<Meeting> getAllMeetings() {
		List<Meeting> meetings = meetingRepository.findAllByDateAsc();
		meetings.forEach(meeting -> meeting.setInfo(meeting.getInfo()
				.stream()
				.sorted(Comparator.comparing(MeetingInformation::getStartMeeting))
				.collect(Collectors.toList())));
		return meetings;
	}
}
