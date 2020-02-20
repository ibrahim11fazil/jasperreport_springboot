package com.report.jasper.repository;    
import com.report.jasper.entities.MultiCoursesReport; 
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext; 
import org.springframework.stereotype.Repository; 

@Repository 
public class MultiCoursesReportCustomRepositoryImpl  implements MultiCoursesReportCustomRepository  {
	 

	@PersistenceContext
    EntityManager entityManager;
	  
		@Override
		public List<MultiCoursesReport> generateMultiCoursesReport(String courseName, Date startDate, Date endDate,
				String courseCoordinator, String instructor, String locationName, String organisation){
	    	System.out.println("IM HERE ");
	    	 List<MultiCoursesReport> multiCoursesDetails = new ArrayList<MultiCoursesReport>();
	    	try {
			    	Map<String,Object> conditions = new HashMap<>();
			    	StringBuilder sqlQuery =new StringBuilder(); 
			    	
			    	
			    	  sqlQuery.append(" select * from TAC_MULTI_COURSES_DETAILS_VIEW  " );
			    	    sqlQuery.append(  " where ( UPPER(COURSE_NAME) like UPPER('%'||:courseName||'%') or trim(:courseName)  is null) "  );
			    	    conditions.put("courseName", courseName); 
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
						sqlQuery.append(  " AND ( UPPER(COORDINATOR) like UPPER('%'||:courseCoordinator||'%') or trim(:courseCoordinator) is   null) "   );
						conditions.put("courseCoordinator", courseCoordinator);
						sqlQuery.append(  " AND ( UPPER(LOCATION_NAME) like UPPER('%'||:locationName||'%') or trim(:locationName) is   null) "  );
						conditions.put("locationName", locationName);
						sqlQuery.append(  " AND ( UPPER(INSTRUCTOR) like UPPER('%'||:instructor||'%') or trim(:instructor) is   null) "   );
						conditions.put("instructor", instructor);
						sqlQuery.append(  " AND ( UPPER(ORGANIZATION) like UPPER('%'||:organisation||'%') or trim(:organisation) is  null) "  );
						conditions.put("organisation", organisation);
			    	 
						 
			    	    System.out.println("sqlQuery" +sqlQuery);
			    	    javax.persistence.Query q = entityManager.createNativeQuery(sqlQuery.toString(),MultiCoursesReport.class);
			    	    conditions.forEach((k,v) -> {
							   q.setParameter(k,v);
						   }); 
			    	     multiCoursesDetails = q.getResultList();  
			    	     
	    	}catch (Exception e) {
				e.printStackTrace();
			}
	    	return multiCoursesDetails;
	    	
	    }


 
}
