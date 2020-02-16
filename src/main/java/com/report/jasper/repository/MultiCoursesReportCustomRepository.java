package com.report.jasper.repository;   
import java.util.Date;
import java.util.List; 
import com.report.jasper.entities.MultiCoursesReport;  

 
public interface MultiCoursesReportCustomRepository   {

	 
	public List<MultiCoursesReport> generateMultiCoursesReport(String courseName, Date startDate, Date endDate,
			String courseCoordinator, String instructor, String locationName, String organisation);
	   
}
