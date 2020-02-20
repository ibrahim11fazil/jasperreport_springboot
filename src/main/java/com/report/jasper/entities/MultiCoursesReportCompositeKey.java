package com.report.jasper.entities;

import java.io.Serializable; 
import java.util.Objects; 
 
public class MultiCoursesReportCompositeKey implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;   
	private Long activationId; 
	
	
	public MultiCoursesReportCompositeKey() {
		// TODO Auto-generated constructor stub
	}
	public MultiCoursesReportCompositeKey(Long id, Long activationId) {
		this.id=id;
		this.activationId=activationId;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        MultiCoursesReportCompositeKey courseCompId = (MultiCoursesReportCompositeKey) o;
	        return id.equals(courseCompId.id) &&
	        		activationId.equals(courseCompId.activationId);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id, activationId);
	    }
	
	 
}
