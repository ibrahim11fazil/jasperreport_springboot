package com.report.jasper.repository;    
import com.report.jasper.entities.MultiCoursesReport;
 
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface MultiCoursesReportRepository
extends JpaRepository<MultiCoursesReport,Integer>,MultiCoursesReportCustomRepository 
{
   
}
