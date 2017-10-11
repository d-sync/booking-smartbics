package com.smartbics.repository;

import com.smartbics.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

	Meeting getMeetingByDate(LocalDate date);

	@Query("SELECT m FROM Meeting m ORDER BY m.date ASC")
	List<Meeting> findAllByDateAsc();

}
