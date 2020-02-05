package com.report.jasper.repository;   
import java.util.Date;
import java.util.List;

import com.report.jasper.entities.CourseStatusReport;  

 
public interface CourseStatusReportCustomRepository   {

	
	    List<CourseStatusReport> generateCourseStatusReport(String employee, String jobNo
	    		,String courseName,
	    		Date startDate,Date endDate,
	    		Long duration,
	    		String job,
	    		String jobTitle,String department,
	    		Long jobCardNo,
	    		String requestStatus,
	    		String courseStatus); 
	  
	   
}
