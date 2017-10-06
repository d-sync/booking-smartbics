package com.smartbics.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;


@Data
public class MeetigResp {

	private LocalDate date;

	private Set<Meeting> meetings;

	public MeetigResp() {
	}

	public MeetigResp(LocalDate date, Set<Meeting> meetings) {
		this.date = date;
		this.meetings = meetings;
	}
}
