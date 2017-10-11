package com.smartbics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	public String toString() {
		return "{" +
				date +
				" " + info +
				"}";
	}
}
