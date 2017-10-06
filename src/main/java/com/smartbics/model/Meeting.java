package com.smartbics.model;


import lombok.Data;

import java.time.LocalTime;

@Data
public class Meeting {

	private LocalTime startMeeting;

	private LocalTime endMeeting;

	private String employerName;


}
