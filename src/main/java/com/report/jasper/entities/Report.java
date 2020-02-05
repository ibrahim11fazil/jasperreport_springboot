package com.report.jasper.entities;

import java.util.Date;

public class Report {  
	
	private Long id;
	private String courseName;
	private Long duration;
	private Long activationId;
	private Date startDate;
	private Date endDate;
	private String category;
	private String hall;
	private String locationName;
	private String instructor;
	
	
//	TAC_COURSE_STATUS_VIEW
//	private String employee;
//	private Long jobNo;
//	private Long courseName;
//	private Date startDate;
//	private Date endDate;
//	private String duration;
//	private String job;
//	private String jobTitle;
//	private String department;
//	private Long jobCardNo;
//	private String requestStatus;
//	private String courseStatus;
	
	
	
//	TAC_INSTRUCTOR_SUBJECT_VIEW
//	private String name; 
//	private String type; 
//	private String courseName; 
//	private String organisation; 
//	private String jobTitle; 
//	private String companyName; 
//	private String qid; 
//	private String course; 
//	private String priority; 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Long getActivationId() {
		return activationId;
	}
	public void setActivationId(Long activationId) {
		this.activationId = activationId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getHall() {
		return hall;
	}
	public void setHall(String hall) {
		this.hall = hall;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	  
	
	

	 
	 
	
}
