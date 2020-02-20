package com.report.jasper.entities;
 
import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity 
@Table(name="TAC_INSTRUCTOR_SUBJECT_VIEW")
@IdClass(InstructorSubjectReportCompositeKey.class)
public class InstructorSubjectReport { 
	
	
	
	@Id
	@Column(name="INSTRUCTOR_ID")
	private Long instructorId;
	@Id
	@Column(name="SUBJECT_ID")
	private Long subjectId;
	@Column(name="NAME")
	private String name;
	@Column(name="TYPE")
	private String type;
	@Column(name="JOB_ID")
	private String jobId;
	@Column(name="ORGANIZATION")
	private String organisation;
	@Column(name="JOB_TITLE")
	private String jobTitle;
	@Column(name="COMPANY_NAME")
	private String companyName;
	@Column(name="QID")
	private String qid;
	@Column(name="COURSE")
	private String course;
//	@Column(name="INSTRUCTOR")
//	private String instructor;
	@Column(name="PRIORITY")
	private String priority;
	 
	
	
	
	 
	public Long getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	} 
	
	
	
}
