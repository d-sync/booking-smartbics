package com.smartbics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

/* аннотация @Data нужна для генерации геттеров и сеттеров.
   чтобы она работала нужно в Idea подключить Lombok Plugin
*/
@Data
@Entity
public class MeetingInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@JsonFormat(pattern = "HH:mm")
	private LocalTime startMeeting;

	@JsonFormat(pattern = "HH:mm")
	private LocalTime endMeeting;

	private String emp;

	public MeetingInformation() {
	}

	public MeetingInformation(LocalTime startMeeting, LocalTime endMeeting, String emp) {
		this.startMeeting = startMeeting;
		this.endMeeting = endMeeting;
		this.emp = emp;
	}

	@Override
	public String toString() {
		return "{" +
				 startMeeting +
				" - " + endMeeting +
				", " + emp + '\'' +
				'}';
	}
}