package com.report.jasper.repository;  
import com.report.jasper.entities.CourseReport;
import com.report.jasper.entities.CourseStatusReport;
import com.report.jasper.entities.InstructorSubjectReport;
import com.report.jasper.entities.Report;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorSubjectReportRepository 
extends JpaRepository<InstructorSubjectReport,Integer> 
{
	StringBuilder strQuery = new StringBuilder();
	  @Query(value = "select * from TAC_COURSE_MASTER_VIEW where DATE_CREATED between :fromDate AND :toDate",nativeQuery = true)
	    List<InstructorSubjectReport> generateReportByDates(Date fromDate,Date toDate);
	  
	  
	  @Query( value = "select * from TAC_INSTRUCTOR_SUBJECT_VIEW  \n" + 
		  		"where (UPPER(NAME) like UPPER('%'||:name||'%')  or :name  is null)\n" + 
		  		"AND (UPPER(TYPE) like UPPER('%'||:type||'%') or :type  is null)\n" +
		  		"AND (UPPER(JOB_ID)  like UPPER('%'||:jobId||'%')  or :jobId is   null)\n" +  
				"AND ( UPPER(ORGANIZATION)  like UPPER('%'||:organisation||'%')   or :organisation is   null)\n" +  
				"AND ( UPPER(JOB_TITLE) like UPPER('%'||:jobTitle||'%')  or :jobTitle is   null)\n" + 
				"AND ( UPPER(COMPANY_NAME) like UPPER('%'||:companyName||'%')  or :companyName is   null)\n" +  
				"AND ( UPPER(QID)  like UPPER('%'||:qid||'%')  or :qid is   null)\n" +  
				"AND ( UPPER(COURSE)   like UPPER('%'||:course||'%')  or :course is  null)\n" + 
				"AND ( UPPER(PRIORITY)	   like UPPER('%'||:priority||'%')  or  :priority is  null) ",nativeQuery = true)
		    List<InstructorSubjectReport> generateInstructorReport(String name, String type
		    		,String jobId,
		    		String organisation, 
		    		String jobTitle,String companyName,
		    		String qid,
		    		String course,
		    		String priority);
		    
		    
	  
//	  @Query( value = "select * from TAC_INSTRUCTOR_SUBJECT_VIEW  \n" + 
//		  		"where (NAME =:name  or :name  is null)\n" + 
//		  		"AND (TYPE =:type or :type  is null)\n" +
//		  		"AND (JOB_ID  =:jobId  or :jobId is   null)\n" +  
//				"AND ( ORGANIZATION  =:organisation   or :organisation is   null)\n" +  
//				"AND ( JOB_TITLE =:jobTitle  or :jobTitle is   null)\n" + 
//				"AND ( COMPANY_NAME =:companyName  or :companyName is   null)\n" +  
//				"AND ( QID  =:qid  or :qid is   null)\n" +  
//				"AND ( COURSE   =:course  or :course is  null)\n" + 
//				"AND ( PRIORITY	   =:priority  or  :priority is  null) ",nativeQuery = true)
	  
//		    @Query( value = "select * from TAC_INSTRUCTOR_SUBJECT_VIEW  \n" + 
//			  		"where (NAME like %:name% or :name  is null)\n" + 
//			  		"AND (TYPE like %:type% or :type  is null)\n" +
//			  		"AND (JOB_ID  like %:jobId%  or :jobId is   null)\n" +  
//					"AND ( ORGANIZATION  like %:organisation%   or :organisation is   null)\n" +  
//					"AND ( JOB_TITLE like %:jobTitle%  or :jobTitle is   null)\n" + 
//					"AND ( COMPANY_NAME like %:companyName%  or :companyName is   null)\n" +  
//					"AND ( QID like %:qid% or :qid is   null)\n" +  
//					"AND ( COURSE like  %:course% or :course is  null)\n" + 
//					"AND ( PRIORITY	like %:priority% or  :priority is  null) ",nativeQuery = true)
	  
//	  @Query( value = "select * from TAC_INSTRUCTOR_SUBJECT_VIEW  \n" + 
//		  		"where (NAME LIKE CONCAT('%',:name ,'%') or :name  is null)\n" + 
//		  		"AND (TYPE LIKE CONCAT('%',:type ,'%') or :type  is null)\n" +
//		  		"AND (JOB_ID  LIKE CONCAT('%',:jobId ,'%') or :jobId is   null)\n" +  
//				"AND ( ORGANIZATION  LIKE CONCAT('%',:organisation ,'%')  or :organisation is   null)\n" +  
//				"AND ( JOB_TITLE LIKE CONCAT('%',:jobTitle ,'%')  or :jobTitle is   null)\n" + 
//				"AND ( COMPANY_NAME LIKE CONCAT('%',:companyName ,'%') or :companyName is   null)\n" +  
//				"AND ( QID LIKE CONCAT('%',:qid ,'%') or :qid is   null)\n" +  
//				"AND ( COURSE LIKE CONCAT('%',:course ,'%') or :course is  null)\n" + 
//				"AND ( PRIORITY	LIKE CONCAT('%',:priority ,'%') or  :priority is  null) ",nativeQuery = true)
	  
	 
		  
}
