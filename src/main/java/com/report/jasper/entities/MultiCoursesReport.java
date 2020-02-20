package com.report.jasper.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity 
@Table(name="TAC_MULTI_COURSES_DETAILS_VIEW")
@IdClass(MultiCoursesReportCompositeKey.class)
public class MultiCoursesReport { 
	
	@Id
	@Column(name="COURSE_ID")
	private Long id; 
	@Id
	@Column(name="ACTIVATION_ID")
	private Long activationId;
	@Column(name="COURSE_NAME")
	private String courseName;
	@Column(name="START_DATE")
	private Date startDate;
	@Column(name="END_DATE")
	private Date endDate;
	@Column(name="COORDINATOR")
	private String courseCoordinator;
	@Column(name="INSTRUCTOR")
	private String instructor;
	@Column(name="LOCATION_NAME")
	private String locationName;
	@Column(name="ORGANIZATION")
	private String organisation;
	
	public MultiCoursesReport() { 
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getActivationId() {
		return activationId;
	}
	public void setActivationId(Long activationId) {
		this.activationId = activationId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
	public String getCourseCoordinator() {
		return courseCoordinator;
	}
	public void setCourseCoordinator(String courseCoordinator) {
		this.courseCoordinator = courseCoordinator;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	
	 
	
}
