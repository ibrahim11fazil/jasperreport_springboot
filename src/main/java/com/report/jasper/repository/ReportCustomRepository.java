package com.report.jasper.repository;   
import java.util.Date;
import java.util.List;

import com.report.jasper.entities.CourseReport;
import com.report.jasper.entities.CourseStatusReport;  

 
public interface ReportCustomRepository   {

	
	public List<CourseReport> generateCourseDetailsReport(String courseName, Long duration
    		,Long activationId,
    		Date startDate,Date endDate,
    		String category,
    		String hall,String locationName,
    		String instructor );
	   
}
