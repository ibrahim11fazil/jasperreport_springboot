package com.report.jasper.repository;  
import com.report.jasper.entities.CourseReport;
import com.report.jasper.entities.CourseStatusReport;
import com.report.jasper.entities.Report; 
import java.util.Date;
import java.util.List; 
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStatusReportRepository 
extends JpaRepository<CourseStatusReport,Integer>,CourseStatusReportCustomRepository
{
//	StringBuilder strQuery = new StringBuilder();

 
//	  @Query( value = "select * from TAC_COURSE_STATUS_VIEW  \n" + 
//	  		"where (EMPLOYEE= :employee or :employee  is null)\n" + 
//	  		"AND (JOBNO = :jobNo or :jobNo  is null)\n" +
//	  		"AND (COURSE_NAME= :courseName or :courseName is   null)\n" + 
//			"AND (START_DATE= :startDate or :startDate='null' or :startDate is   null)\n" + 
//			"AND ( END_DATE= :endDate or :endDate='null' or :endDate is   null)\n" + 
//			"AND ( DURATION = :duration or :duration = '0' or :duration is   null)\n" + 
//			"AND ( JOB = :job or :job is  null)\n" + 
//			"AND ( JOB_TITLE= :jobTitle  or :jobTitle is   null)\n" + 
//			"AND ( DEPARTMENT= :department or :department is   null)\n" +  
//			"AND ( JOBCARD_NO= :jobCardNo or  :jobCardNo = '0' or :jobCardNo is   null)\n" +  
//			"AND ( REQUEST_STATUS= :requestStatus or :requestStatus is  null)\n" + 
//			"AND ( COURSE_STATUS= :courseStatus or  :courseStatus is  null) ",nativeQuery = true)
//	    List<CourseStatusReport> generateCourseStatusReport(String employee, String jobNo
//	    		,String courseName,
//	    		Date startDate,Date endDate,
//	    		Long duration,
//	    		String job,
//	    		String jobTitle,String department,
//	    		Long jobCardNo,
//	    		String requestStatus,
//	    		String courseStatus); 
	
	 
	  
	   
}
