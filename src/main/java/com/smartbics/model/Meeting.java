package com.smartbics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/* аннотация @Data нужна для генерации геттеров и сеттеров.
   чтобы она работала нужно в Idea подключить Lombok Plugin
*/
@Data
@Entity
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = MeetingInformation.class, cascade = CascadeType.ALL)
	@JoinTable(name = "meeting_to_meeting_info",
			joinColumns = {@JoinColumn(name = "meeting_id")},
			inverseJoinColumns = {@JoinColumn(name = "m_info_id")})
	private List<MeetingInformation> info;

	public Meeting() {
	}

	public Meeting(LocalDate date, List<MeetingInformation> info) {
		this.date = date;
		this.info = info;
	}

	public void addMeetingInformation(MeetingInformation meetingInformation) {
		if (info != null) {
			info.add(meetingInformation);
		} else {
			info = new ArrayList<>();
			info.add(meetingInformation);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Meeting meeting = (Meeting) o;

		if (date != null ? !date.equals(meeting.date) : meeting.date != null) return false;
		return info != null ? info.equals(meeting.info) : meeting.info == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (info != null ? info.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "{" +
				date +
				" " + info +
				"}";
	}
}
