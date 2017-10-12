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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		MeetingInformation that = (MeetingInformation) o;

		if (startMeeting != null ? !startMeeting.equals(that.startMeeting) : that.startMeeting != null) return false;
		if (endMeeting != null ? !endMeeting.equals(that.endMeeting) : that.endMeeting != null) return false;
		return emp != null ? emp.equals(that.emp) : that.emp == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (startMeeting != null ? startMeeting.hashCode() : 0);
		result = 31 * result + (endMeeting != null ? endMeeting.hashCode() : 0);
		result = 31 * result + (emp != null ? emp.hashCode() : 0);
		return result;
	}
}