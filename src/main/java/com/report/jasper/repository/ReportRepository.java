package com.report.jasper.repository;  
import com.report.jasper.entities.CourseReport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


 
@Repository
public interface ReportRepository
extends JpaRepository<CourseReport,Integer>,ReportCustomRepository 
{
	 	  
}



















//not custom jpa
//@Query(value = "select * from TAC_COURSE_MASTER_VIEW where DATE_CREATED between :fromDate AND :toDate",nativeQuery = true)
//  List<CourseReport> generateReportByDates(Date fromDate,Date toDate);
//
//@Query( value = "select * from TAC_COURSE_STATUS_VIEW  \n" + 
//	  		"where (COURSE_NAME= :courseName or :courseName  is null)\n" + 
//	  		"AND (DURATION = :duration or :duration  is null)\n" +
//	  		"AND (ACTIVATION_ID= :activationId or :activationId is   null)\n" + 
//			"AND (START_DATE= :startDate   or :startDate is   null)\n" + 
//			"AND ( END_DATE= :endDate   or  :endDate is   null)\n" + 
//			"AND ( CATEGORY = :category or :category is   null)\n" + 
//			"AND ( HALL = :hall or :hall is  null)\n" + 
//			"AND ( LOCATION_NAME= :locationName  or :locationName is   null)\n" + 
//			"AND ( INSTRUCTOR= :instructor or :instructor is   null)\n" ,nativeQuery = true)
//	    List<CourseReport> generateCourseDetailsReport(String courseName, Long duration
//	    		,Long activationId,
//	    		Date startDate,Date endDate,
//	    		String category,
//	    		String hall,String locationName,
//	    		String instructor );