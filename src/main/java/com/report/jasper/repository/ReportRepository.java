package com.report.jasper.repository;  
import com.report.jasper.entities.CourseReport;
import com.report.jasper.entities.Report;


import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository  extends JpaRepository<CourseReport,Integer> {

}
