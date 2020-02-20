package com.report.jasper.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity 
@Table(name="TAC_COURSE_DETAILS_VIEW")
@IdClass(CourseReportCompositeKey.class)
public class CourseReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="COURSE_ID")
	private Long id; 
	@Id
	@Column(name="ACTIVATION_ID")
	private Long activationId;
	@Column(name="COURSE_NAME")
	private String courseName; 
	@Column(name="DURATION")
	private Long duration;
	@Column(name="START_DATE")
	private Date startDate;
	@Column(name="END_DATE")
	private Date endDate; 
	@Column(name="CATEGORY")
	private String category; 
	@Column(name="HALL")
	private String hall; 
	@Column(name="LOCATION_NAME")
	private String locationName; 
	@Column(name="INSTRUCTOR")
	private String instructor;
	
	 public CourseReport() { 
	 }
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Long getActivationId() {
		return activationId;
	}
	public void setActivationId(Long activationId) {
		this.activationId = activationId;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
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
