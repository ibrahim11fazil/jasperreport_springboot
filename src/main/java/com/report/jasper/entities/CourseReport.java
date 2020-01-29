package com.report.jasper.entities;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="TAC_COURSE_MASTER_VIEW")
public class CourseReport {
	@Id
	@Column(name="COURSE_ID")
	private long id;

	@Column(name="COURSE_NAME")
	private String courseName;

	@Column(name="DURATION")
	private Long duration;

	@Column(name="NUMBEROFHOURS")
	private Long noOfHours;
	
	@Column(name="USER_CREATED")
	private String userCreated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getNoOfHours() {
		return noOfHours;
	}

	public void setNoOfHours(Long noOfHours) {
		this.noOfHours = noOfHours;
	}

	public String getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	
	 
	
}
