package com.report.jasper.controller;

import java.io.*;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RestController;

import com.report.jasper.entities.CourseReport;
import com.report.jasper.entities.CourseStatusReport;
import com.report.jasper.entities.InstructorSubjectReport;
import com.report.jasper.entities.MultiCoursesReport;
import com.report.jasper.entities.Report;
import com.report.jasper.entities.Report2;
import com.report.jasper.entities.Report3;
import com.report.jasper.model.ResponseType;
import com.report.jasper.services.FileService;
import com.report.jasper.services.ReportingService;
import com.report.jasper.repository.CourseStatusReportRepository;
import com.report.jasper.repository.InstructorSubjectReportRepository;
import com.report.jasper.repository.ReportRepository;

import net.sf.jasperreports.engine.JRException;

@RestController
//@CrossOrigin(origins="http://localhost:4200")
@CrossOrigin(origins = "*")
public class ReportController {

	@Autowired
	private ReportRepository repository;
	@Autowired
	private CourseStatusReportRepository courseStatusrepository;
	@Autowired
	private InstructorSubjectReportRepository instructorSubjectrepository;
    @Autowired
    private ReportingService service;
    
    private final FileService fileService;

	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    
    public ReportController(FileService fileService) {
    	this.fileService = fileService;
	}
    
	@GetMapping(value = "/pdfReportDownload/{filename}", produces = "application/pdf; charset=utf-8")
 	public Resource getPDFFileFromFileSystem(@PathVariable String filename, HttpServletResponse response) {
		logger.info("filename*********" +filename);
		return fileService.getFileSystem(filename, response);
	}
	
	@GetMapping(value = "/excelReportDownload/{filename}", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=utf-8")
 	public Resource getExcelFileFromFileSystem(@PathVariable String filename, HttpServletResponse response) {
		logger.info("filename*********" +filename);
		return fileService.getFileSystem(filename, response);
	}
 
	@GetMapping(value = "/api/files/classpath/{filename}", produces = "application/pdf; charset=utf-8")
	public Resource getFileFromClasspath(@PathVariable String filename, HttpServletResponse response) {
		return fileService.getClassPathFile(filename, response);
	}
    
//    @GetMapping("/getCourse")
//	public ResponseType getCourse() {
//		boolean status =false;
//		String message = "No Data Found";
//		List<CourseReport> course=null;
////		if(name!=null && name!="") {
//			course = service.getAllCountrys();
//			  if(course!=null && course.size()>0) {
//				 status =true;
//				 message="Data found";
//			  }
////		}
//			  logger.info(course);
//		ResponseType response = new ResponseType(200,message,status,course);
//		return response;
//	}
    
    
    
    @GetMapping("/getCourse")
 	public List<Report> getCourse() { 
 		List<Report> course=new ArrayList<>() ; 
// 			course = service.getAllCountrys(); 
// 		return repository.findAll();  
 		return null;
 	}
	

     
    @GetMapping("/getCourses")
    public List<CourseReport> getReports() { 
//        return repository.findAll(); 
    	return null;
    }
    
    @GetMapping("/getCourseStatusReport")
    public List<CourseStatusReport> getCourseStatusReports() { 
//        return courseStatusrepository.findAll(); 
    	return null;
    	
    }
    
    @GetMapping("/getInstructorSubjectReport")
    public List<InstructorSubjectReport> getInstructorSubjectReports() { 
//        return instructorSubjectrepository.findAll();  
    	return null;
    }
    
    
//    @GetMapping("/report/{fromDate}/{toDate}/{format}")
//    public String generateReport(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable String format) throws FileNotFoundException, JRException {
//       logger.info("from date"+fromDate);
//    	
//    	Date startDate=new Date(fromDate);
//        Date endDate=new Date(toDate);
//         
//    	
//    	return service.exportReport(format,startDate,endDate);
//    }
    
     
    @PostMapping("/report/{format}")
//    public String generateReport(@Valid @RequestBody Map<String,Object>  courseData,@PathVariable String format) throws FileNotFoundException, JRException {
    	public String generateReport(@Valid @RequestBody CourseReport  courseDetailsData,@PathVariable String format) throws FileNotFoundException, JRException {	
       try {
		   logger.info("from date"+courseDetailsData.getStartDate());
		   logger.info("to date"+courseDetailsData.getActivationId());
		   logger.info("to date"+courseDetailsData.getCourseName());
        
       if(courseDetailsData!=null && courseDetailsData.getActivationId()==null) {
    	   courseDetailsData.setActivationId(0l);
	    }
    
    
	    if(courseDetailsData!=null && courseDetailsData.getDuration()==null) {
	    	courseDetailsData.setDuration(new Long(0l));
	    }
	     
//    	Date startDate=new Date(courseData.get("fromDate").toString());
//        Date endDate=new Date(courseData.get("toDate").toString());
//         logger.info(startDate);
//         logger.info(endDate);
       
       if(courseDetailsData!=null && courseDetailsData.getStartDate()!=null)
    	   courseDetailsData.setStartDate(dateConversion(courseDetailsData.getStartDate()));
	    
	    if(courseDetailsData!=null && courseDetailsData.getEndDate()!=null)
	    	courseDetailsData.setEndDate(dateConversion(courseDetailsData.getEndDate()));
       }catch (Exception e) {
   		// TODO: handle exception
   	   }  
	    return service.exportReport(format,courseDetailsData);
     
    	 
    }
    
    @PostMapping("/courseStatusReport/{format}")
    public String generateCourseStatusReport(@Valid @RequestBody CourseStatusReport  courseData,@PathVariable String format) throws FileNotFoundException, JRException {	
    	  try {
    		  CourseStatusReport  courseData1=    courseData;
			  logger.info("from date"+courseData1.getStartDate());
			  logger.info("to date"+courseData1.getDepartment());
			  logger.info("to date"+courseData1.getJobCardNo());
			  logger.info("to date"+courseData1.getDuration()); 
     
     if(courseData!=null && courseData.getJobCardNo()==null) {
	    	courseData.setJobCardNo(0l);
	    }
     
     
	    if(courseData!=null && courseData.getDuration()==null) {
	    	courseData.setDuration(new Long(0l));
	    }
	    
	     
	    
	    if(courseData!=null && courseData.getStartDate()!=null)
	    	courseData.setStartDate(dateConversion(courseData.getStartDate()));
	    
	    if(courseData!=null && courseData.getEndDate()!=null)
	    	courseData.setEndDate(dateConversion(courseData.getEndDate()));
	    
//	    if(courseData!=null && courseData.getEndDate()!=null) { 
//	    	Date endDate=null;
//	    	String pattern = "dd-MMM-yy";
//	    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
//	    	String date = simpleDateFormat.format(courseData.getEndDate());
////	    	endDate=new Date("09-JAN-20"); 
//	    	
//	    	logger.info(date);
//	    	try {
//				  endDate = simpleDateFormat.parse(date);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	    	logger.info(endDate);
//	    	courseData.setEndDate(endDate);
//	    }
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    }
  	
  	return service.exportCourseStatusReport(format,courseData); 
  }
    
    
    @PostMapping("/instructSubjReport/{format}") 
//    public List<InstructorSubjectReport> generateInstructorSubjectReport(@Valid @RequestBody InstructorSubjectReport  instructSubjData,@PathVariable String format) throws FileNotFoundException, JRException {
      public String generateInstructorSubjectReport(@Valid @RequestBody InstructorSubjectReport   instructSubjData,@PathVariable String format) throws FileNotFoundException, JRException {
          logger.info("Request for PDF generation");
//  	Report3  courseData1=   (Report3 ) courseData;
//  	logger.info("from date"+courseData1.getCompanyName());
//     logger.info("to date"+courseData1.getPriority());
//     logger.info("to date"+courseData1.getJobTitle());
       
  	return service.exportIntructorSubjectReport(format,instructSubjData); 
  }
    
    
    @PostMapping("/multiCoursesReport/{format}")  
    public String generateMultiCoursesReport(@Valid @RequestBody MultiCoursesReport   multiCoursesReport,@PathVariable String format) throws FileNotFoundException, JRException {
        logger.info("Request for multiCoursesReport PDF generation");
//	Report3  courseData1=   (Report3 ) courseData;
//	logger.info("from date"+courseData1.getCompanyName());
//   logger.info("to date"+courseData1.getPriority());
//   logger.info("to date"+courseData1.getJobTitle());
        if(multiCoursesReport!=null && multiCoursesReport.getStartDate()!=null)
        	multiCoursesReport.setStartDate(dateConversion(multiCoursesReport.getStartDate()));
 	    
 	    if(multiCoursesReport!=null && multiCoursesReport.getEndDate()!=null)
 	    	multiCoursesReport.setEndDate(dateConversion(multiCoursesReport.getEndDate()));
	return service.exportMultiCoursesReport(format, multiCoursesReport);
}
    
    
    
    public Date dateConversion(Date datetoConvert) {
    	Date convertedDate=null;
    	try {
    	  if(datetoConvert!=null ) {  
  	    	String pattern = "dd-MMM-yy";
  	    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
  	    	String date = simpleDateFormat.format(datetoConvert);
			  logger.info(date);
  	    	try {
  				  convertedDate = simpleDateFormat.parse(date);
  			} catch (ParseException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			} 
//  	    	courseData.setEndDate(endDate);
  	    }
  	    }catch(Exception ex) {
  	    	ex.printStackTrace();
  	    }
    	return convertedDate;
    }
    
    
    
//    private static final String EXTERNAL_FILE_PATH = "C:\\Users\\ibrahim.fazil\\Desktop\\Reportss\\";
//
//	@PostMapping("/file/{fileName}")
//	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
//			@PathVariable("fileName") String fileName) throws IOException {
//
//		File file = new File(EXTERNAL_FILE_PATH + fileName);
//		if (file.exists()) {
//
//			//get the mimetype
//			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//			if (mimeType == null) {
//				//unknown mimetype so set the mimetype to application/octet-stream
//				mimeType = "application/octet-stream";
//			}
//
//			response.setContentType(mimeType);
//
//		 
//			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
//
//			 //Here we have mentioned it to show as attachment
//			 //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
//
//			response.setContentLength((int) file.length());
//
//			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//
//			FileCopyUtils.copy(inputStream, response.getOutputStream());
//
//		}
//	}
}
