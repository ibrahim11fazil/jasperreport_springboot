package com.report.jasper.services;
 

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import com.report.jasper.entities.CourseReport;
import com.report.jasper.entities.CourseStatusReport;
import com.report.jasper.entities.InstructorSubjectReport;
import com.report.jasper.entities.MultiCoursesReport;
import com.report.jasper.entities.Report;
import com.report.jasper.entities.Report2;
import com.report.jasper.entities.Report3;
import com.report.jasper.model.ResponseType;

import net.sf.jasperreports.engine.JRException;


public interface ReportingService {
	public String exportReport(String reportFormat,CourseReport courseData) throws FileNotFoundException, JRException ;
	public String exportCourseStatusReport(String reportFormat,CourseStatusReport courseReport) throws FileNotFoundException, JRException;
    public String exportInstructorSubjectReport(String reportFormat,InstructorSubjectReport courseReport) throws FileNotFoundException, JRException ;
    public String  exportMultiCoursesReport(String reportFormat,MultiCoursesReport multiCoursesReport) throws FileNotFoundException, JRException ;
    List<CourseReport> getCourseReportData();
    List<CourseStatusReport> getCourseStatusReportData();
    List<InstructorSubjectReport> getInstructSubjReportData();
}





