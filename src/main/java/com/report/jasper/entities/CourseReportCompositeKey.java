package com.report.jasper.entities;

import java.io.Serializable; 
import java.util.Objects; 
 
public class CourseReportCompositeKey implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;   
	private Long activationId; 
	
	
	public CourseReportCompositeKey() {
		// TODO Auto-generated constructor stub
	}
	public CourseReportCompositeKey(Long id, Long activationId) {
		this.id=id;
		this.activationId=activationId;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        CourseReportCompositeKey courseCompId = (CourseReportCompositeKey) o;
	        return id.equals(courseCompId.id) &&
	        		activationId.equals(courseCompId.activationId);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id, activationId);
	    }
	
	 
}
