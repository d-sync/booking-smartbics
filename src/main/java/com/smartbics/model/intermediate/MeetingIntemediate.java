package com.smartbics.model.intermediate;

import lombok.Data;

@Data
public class MeetingIntemediate {

	private String bookingDate;

	private String employerName;

	private String meetingDate;

	private Integer duration;

	public MeetingIntemediate() {
	}
}
