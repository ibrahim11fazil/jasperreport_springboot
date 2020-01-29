package com.report.jasper.controller;

import java.io.FileNotFoundException;
import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.report.jasper.entities.CourseReport;
import com.report.jasper.entities.Report;
import com.report.jasper.model.ResponseType;
import com.report.jasper.services.ReportingService;
import com.report.jasper.repository.ReportRepository;

import net.sf.jasperreports.engine.JRException;

@RestController
public class ReportController {

    @Autowired
    private ReportRepository repository;
    @Autowired
    private ReportingService service;
    
    
    @GetMapping("/getCourse")
	public ResponseType getCourse() {
		boolean status =false;
		String message = "No Data Found";
		List<CourseReport> course=null;
//		if(name!=null && name!="") {
			course = service.getAllCountrys();
			  if(course!=null && course.size()>0) {
				 status =true;
				 message="Data found";
			  }
//		}
			  System.out.println(course);
		ResponseType response = new ResponseType(200,message,status,course);
		return response;
	}
	

   


//    @GetMapping("/getReports")
//    public List<Report> getReports() {
//
//        return repository.findAll();
////    	return null;
//    }
    @GetMapping("/getCountryReports")
    public List<CourseReport> getReports() {

        return repository.findAll();
//    	return null;
    }
    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return service.exportReport(format);
    }
}
