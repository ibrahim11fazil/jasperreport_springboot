package com.report.jasper.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="TAC_COURSE_STATUS_VIEW")
public class CourseStatusReport {
	
	public CourseStatusReport() {
		// TODO Auto-generated constructor stub
	}
//	@Id
//	@Column(name="COURSE_ID")
//	private long id;
 
	@Id
	@Column(name="EMPLOYEE")
	private String employee;
	@Column(name="JOBNO")
	private String jobNo;
	@Column(name="COURSE_NAME")
	private String courseName;
	@Column(name="START_DATE")
	private Date startDate;
	@Column(name="END_DATE")
	private Date endDate;
	@Column(name="DURATION")
	private Long duration;
	@Column(name="JOB")
	private String job;
	@Column(name="JOB_TITLE")
	private String jobTitle;
	@Column(name="DEPARTMENT")
	private String department;
	@Column(name="JOBCARD_NO")
	private Long jobCardNo;
	@Column(name="REQUEST_STATUS")
	private String requestStatus;
	@Column(name="COURSE_STATUS")
	private String courseStatus;
	
	
	 
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
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
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Long getJobCardNo() {
		return jobCardNo;
	}
	public void setJobCardNo(Long jobCardNo) {
		this.jobCardNo = jobCardNo;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}
	
	
	
	
	
}
