package com.report.jasper.entities;

import java.io.Serializable; 
import java.util.Objects; 
 
public class InstructorSubjectReportCompositeKey implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	
	private Long instructorId; 
	private Long subjectId;
	
	public InstructorSubjectReportCompositeKey() { 
	}
	
	public InstructorSubjectReportCompositeKey(Long instructorId, Long subjectId) {
		this.instructorId=instructorId;
		this.subjectId=subjectId;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        InstructorSubjectReportCompositeKey courseCompId = (InstructorSubjectReportCompositeKey) o;
	        return instructorId.equals(courseCompId.instructorId) &&
	        		subjectId.equals(courseCompId.subjectId);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(instructorId, subjectId);
	    }
	
	 
}
