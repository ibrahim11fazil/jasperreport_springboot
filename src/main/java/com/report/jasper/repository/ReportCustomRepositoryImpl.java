package com.report.jasper.repository;   
import com.report.jasper.entities.CourseReport;
import com.report.jasper.entities.CourseStatusReport;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository; 

@Repository 
public class ReportCustomRepositoryImpl  implements ReportCustomRepository  {
	 

	@PersistenceContext
    EntityManager entityManager;
	  
		@Override
		public List<CourseReport> generateCourseDetailsReport(String courseName, Long duration
		    		,Long activationId,
		    		Date startDate,Date endDate,
		    		String category,
		    		String hall,String locationName,
		    		String instructor ){
	    	System.out.println("IM HERE ");
	    	 List<CourseReport> courseDetails = new ArrayList<CourseReport>();
	    	try {
			    	Map<String,Object> conditions = new HashMap<>();
			    	StringBuilder sqlQuery =new StringBuilder(); 
			    	
			    	
			    	  sqlQuery.append(" select * from TAC_COURSE_DETAILS_VIEW  " );
			    	    sqlQuery.append(  " where ( UPPER(COURSE_NAME) like UPPER('%'||:courseName||'%') or trim(:courseName)  is null) "  );
			    	    conditions.put("courseName", courseName);
			    	    sqlQuery.append(  " AND (DURATION like '%'||:duration||'%' or :duration = '0' or trim(:duration)  is null) "  );
			    	    conditions.put("duration", duration);
			    	    sqlQuery.append(  " AND (ACTIVATION_ID  like '%'||:activationId||'%'  or :activationId = '0' or trim(:activationId) is null) ");
			    	    conditions.put("activationId", activationId);
			    	    if(startDate!=null && endDate!=null)
			    	    {  	
			    	    	System.out.println("*****Start and end date " + startDate +"...."+ endDate);
			    	    	  sqlQuery.append( " AND ( (START_DATE BETWEEN :startDate AND :endDate )) " );
			    	    	  conditions.put("startDate", startDate);
			    	    	  conditions.put("endDate", endDate);
			    		}
			    	    else if(startDate!=null)
			    	    { 
			    	    	  sqlQuery.append( " AND ( START_DATE= :startDate or :startDate is   null) " );
		    		    	  conditions.put("startDate", startDate);
		    		    }
			    	    if(startDate!=null && endDate!=null)
			    	    { 
			    	    	
			    	    	System.out.println("*****Start and end date " + startDate +"...."+ endDate);
			    	    	  sqlQuery.append( " AND ( (END_DATE BETWEEN :startDate AND :endDate )) " );
			    	    	  conditions.put("startDate", startDate);
			    	    	  conditions.put("endDate", endDate);
			    		}else 
			    		if(endDate!=null) {
			    		    	    	sqlQuery.append(  " AND ( END_DATE= :endDate  or :endDate is   null) " );
			    		    	    	conditions.put("endDate", endDate);
				    		    	    }
//			    	    if(startDate!=null) {
//			    		    	  sqlQuery.append( " AND (START_DATE= :startDate or :startDate is   null) " );
//			    		    	  conditions.put("startDate", startDate);
//			    		    }
//			    		if(endDate!=null) {
//			    		    	    	sqlQuery.append(  " AND ( END_DATE= :endDate  or :endDate is   null) " );
//			    		    	    	conditions.put("endDate", endDate);
//				    		    	    }
						sqlQuery.append(  " AND ( UPPER(CATEGORY) like UPPER('%'||:category||'%') or trim(:category) is   null) "   );
						conditions.put("category", category);
						sqlQuery.append(  " AND ( UPPER(HALL) like UPPER('%'||:hall||'%') or trim(:hall) is  null) "  );
						conditions.put("hall", hall);
						sqlQuery.append(  " AND ( UPPER(LOCATION_NAME) like UPPER('%'||:locationName||'%') or trim(:locationName) is   null) "  );
						conditions.put("locationName", locationName);
						sqlQuery.append(  " AND ( UPPER(INSTRUCTOR) like UPPER('%'||:instructor||'%') or trim(:instructor) is   null) "   );
						conditions.put("instructor", instructor);
			    	
//			    	
						 
			    	    System.out.println("sqlQuery" +sqlQuery);
			    	    javax.persistence.Query q = entityManager.createNativeQuery(sqlQuery.toString(),CourseReport.class);
			    	    conditions.forEach((k,v) -> {
							   q.setParameter(k,v);
						   }); 
			    	  	 courseDetails = q.getResultList();
			    	     System.out.println(courseDetails);
			    	
			    	 
	    	}catch (Exception e) {
				e.printStackTrace();
			}
	    	return courseDetails;
	    	
	    }


		
		// concat query
		
//		  sqlQuery.append(" select * from TAC_COURSE_DETAILS_VIEW  " );
//  	    sqlQuery.append(  " where (COURSE_NAME = :courseName or :courseName  is null) "  );
//  	    conditions.put("courseName", courseName);
//  	    sqlQuery.append(  " AND (DURATION = :duration or :duration = '0' or :duration  is null) "  );
//  	    conditions.put("duration", duration);
//  	    sqlQuery.append(  " AND (ACTIVATION_ID  = :activationId  or :activationId = '0' or :activationId is null) ");
//  	    conditions.put("activationId", activationId);
//  	    if(startDate!=null) {
//  		    	  sqlQuery.append( " AND (START_DATE= :startDate or :startDate is   null) " );
//  		    	  conditions.put("startDate", startDate);
//  		    }
//  		if(endDate!=null) {
//  		    	    	sqlQuery.append(  " AND ( END_DATE= :endDate  or :endDate is   null) " );
//  		    	    	conditions.put("endDate", endDate);
//	    		    	    }
//			sqlQuery.append(  " AND ( CATEGORY = :category or :category is   null) "   );
//			conditions.put("category", category);
//			sqlQuery.append(  " AND ( HALL = :hall or :hall is  null) "  );
//			conditions.put("hall", hall);
//			sqlQuery.append(  " AND ( LOCATION_NAME = :locationName or :locationName is   null) "  );
//			conditions.put("locationName", locationName);
//			sqlQuery.append(  " AND ( INSTRUCTOR = :instructor or :instructor is   null) "   );
//			conditions.put("instructor", instructor);
  	 
//  	    sqlQuery.append(" select * from TAC_COURSE_DETAILS_VIEW  " );
//  	    sqlQuery.append(  " where (COURSE_NAME LIKE CONCAT('%',:courseName ,'%') or :courseName  is null) "  );
//  	    conditions.put("courseName", courseName);
//  	    sqlQuery.append(  " AND (DURATION LIKE CONCAT('%',:duration ,'%') or :duration = '0' or :duration  is null) "  );
//  	    conditions.put("duration", duration);
//  	    sqlQuery.append(  " AND (ACTIVATION_ID  LIKE CONCAT('%',:activationId ,'%')  or :activationId = '0' or :activationId is null) ");
//  	    conditions.put("activationId", activationId);
//  	    if(startDate!=null) {
//  		    	  sqlQuery.append( " AND (START_DATE= :startDate or :startDate is   null) " );
//  		    	  conditions.put("startDate", startDate);
//  		    }
//  		if(endDate!=null) {
//  		    	    	sqlQuery.append(  " AND ( END_DATE= :endDate  or :endDate is   null) " );
//  		    	    	conditions.put("endDate", endDate);
//	    		    	    }
//			sqlQuery.append(  " AND ( CATEGORY LIKE CONCAT('%',:category ,'%') or :category is   null) "   );
//			conditions.put("category", category);
//			sqlQuery.append(  " AND ( HALL LIKE CONCAT('%',:hall ,'%') or :hall is  null) "  );
//			conditions.put("hall", hall);
//			sqlQuery.append(  " AND ( LOCATION_NAME LIKE CONCAT('%',:locationName ,'%') or :locationName is   null) "  );
//			conditions.put("locationName", locationName);
//			sqlQuery.append(  " AND ( INSTRUCTOR LIKE CONCAT('%',:instructor ,'%') or :instructor is   null) "   );
//			conditions.put("instructor", instructor);
 
}
