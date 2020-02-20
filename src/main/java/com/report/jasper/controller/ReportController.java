package com.report.jasper.controller;

import java.io.*; 
import java.text.ParseException;
import java.text.SimpleDateFormat; 
import java.util.Date;  
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
import org.springframework.web.bind.annotation.RestController; 
import com.report.jasper.entities.CourseReport;
import com.report.jasper.entities.CourseStatusReport;
import com.report.jasper.entities.InstructorSubjectReport;
import com.report.jasper.entities.MultiCoursesReport; 
import com.report.jasper.services.FileService;
import com.report.jasper.services.ReportingService; 

import net.sf.jasperreports.engine.JRException;

@RestController
//@CrossOrigin(origins="http://localhost:4200")
@CrossOrigin(origins = "*")
public class ReportController {


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
     
    @PostMapping("/report/{format}")
    public String generateReport(@Valid @RequestBody CourseReport  courseDetailsData,@PathVariable String format) throws FileNotFoundException, JRException {	
    	String result="";
    	try {
		    
    	   logger.info("from date"+courseDetailsData.getStartDate());
		   logger.info("to date"+courseDetailsData.getActivationId());
		   logger.info("to date"+courseDetailsData.getCourseName()); 
		   if(courseDetailsData!=null && courseDetailsData.getActivationId()==null)
			   courseDetailsData.setActivationId(0l);  
		   if(courseDetailsData!=null && courseDetailsData.getDuration()==null)
			   courseDetailsData.setDuration(new Long(0l));  
		   if(courseDetailsData!=null && courseDetailsData.getStartDate()!=null)
			   courseDetailsData.setStartDate(dateConversion(courseDetailsData.getStartDate())); 
		   if(courseDetailsData!=null && courseDetailsData.getEndDate()!=null)
			   courseDetailsData.setEndDate(dateConversion(courseDetailsData.getEndDate()));
		   
		   result= service.exportReport(format,courseDetailsData);
       }catch (Exception e) {
    	   e.printStackTrace();
   	   }  
	    return result;
     
    	 
    }
    
    @PostMapping("/courseStatusReport/{format}")
    public String generateCourseStatusReport(@Valid @RequestBody CourseStatusReport  courseStatusData,@PathVariable String format) throws FileNotFoundException, JRException {	
    	String result="";
    	try {
	        if(courseStatusData!=null && courseStatusData.getJobCardNo()==null)
	        	courseStatusData.setJobCardNo(0l);
		    if(courseStatusData!=null && courseStatusData.getDuration()==null)
		    	courseStatusData.setDuration(new Long(0l));
		    if(courseStatusData!=null && courseStatusData.getStartDate()!=null)
		    	courseStatusData.setStartDate(dateConversion(courseStatusData.getStartDate()));
		    if(courseStatusData!=null && courseStatusData.getEndDate()!=null)
		    	courseStatusData.setEndDate(dateConversion(courseStatusData.getEndDate()));
		    
		    result = service.exportCourseStatusReport(format,courseStatusData);
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    }
    	return result; 
    }
    
    
    @PostMapping("/instructSubjReport/{format}") 
    public String generateInstructorSubjectReport(@Valid @RequestBody InstructorSubjectReport   instructSubjData,@PathVariable String format) throws FileNotFoundException, JRException {
    	String result="";
    	try {
	    	logger.info("Request for PDF generation"+  instructSubjData.getName());  
	    	result = service.exportInstructorSubjectReport(format,instructSubjData);
    	  }catch(Exception ex) {
  	    	ex.printStackTrace();
  	    }
    	return result;
    }
    
    
    @PostMapping("/multiCoursesReport/{format}")  
    public String generateMultiCoursesReport(@Valid @RequestBody MultiCoursesReport   multiCoursesReport,@PathVariable String format) throws FileNotFoundException, JRException {
    	String result="";
    	try {
	    	logger.info("Request for multiCoursesReport PDF generation"); 
	        if(multiCoursesReport!=null && multiCoursesReport.getStartDate()!=null)
	        	multiCoursesReport.setStartDate(dateConversion(multiCoursesReport.getStartDate())); 
	 	    if(multiCoursesReport!=null && multiCoursesReport.getEndDate()!=null)
	 	    	multiCoursesReport.setEndDate(dateConversion(multiCoursesReport.getEndDate()));
	 	    return service.exportMultiCoursesReport(format, multiCoursesReport);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
      	return result;
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
  				e.printStackTrace();
  			}  
  	    }
  	    }catch(Exception ex) {
  	    	ex.printStackTrace();
  	    }
    	return convertedDate;
    }
  
}


// repository reference
//@Autowired
//private ReportRepository repository;
//@Autowired
//private CourseStatusReportRepository courseStatusrepository;
//@Autowired
//private InstructorSubjectReportRepository instructorSubjectrepository;


//Using classpath download
//@GetMapping(value = "/api/files/classpath/{filename}", produces = "application/pdf; charset=utf-8")
//public Resource getFileFromClasspath(@PathVariable String filename, HttpServletResponse response) {
//	return fileService.getClassPathFile(filename, response);
//}



//Using Map Request Body
//public String generateReport(@Valid @RequestBody Map<String,Object>  courseData,@PathVariable String format) throws FileNotFoundException, JRException {
//Date startDate=new Date(courseData.get("fromDate").toString());
//Date endDate=new Date(courseData.get("toDate").toString());
//logger.info(startDate);
//logger.info(endDate); 

//Course Status test
//CourseStatusReport  courseData1=    courseStatusData;
//logger.info("from date"+courseData1.getStartDate());
//logger.info("to date"+courseData1.getDepartment());
//logger.info("to date"+courseData1.getJobCardNo());
//logger.info("to date"+courseData1.getDuration());


//Date Testing
//if(courseData!=null && courseData.getEndDate()!=null) { 
//Date endDate=null;
//String pattern = "dd-MMM-yy";
//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
//String date = simpleDateFormat.format(courseData.getEndDate());
////endDate=new Date("09-JAN-20"); 
//
//logger.info(date);
//try {
//		  endDate = simpleDateFormat.parse(date);
//	} catch (ParseException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//logger.info(endDate);
//courseData.setEndDate(endDate);
//}



//File download alternative through spring boot - not used
//private static final String EXTERNAL_FILE_PATH = "C:\\Users\\ibrahim.fazil\\Desktop\\Reportss\\";
//
//@PostMapping("/file/{fileName}")
//public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
//		@PathVariable("fileName") String fileName) throws IOException {
//
//	File file = new File(EXTERNAL_FILE_PATH + fileName);
//	if (file.exists()) {
//
//		//get the mimetype
//		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//		if (mimeType == null) {
//			//unknown mimetype so set the mimetype to application/octet-stream
//			mimeType = "application/octet-stream";
//		}
//
//		response.setContentType(mimeType);
//
//	 
//		response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
//
//		 //Here we have mentioned it to show as attachment
//		 //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
//
//		response.setContentLength((int) file.length());
//
//		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//
//		FileCopyUtils.copy(inputStream, response.getOutputStream());
//
//	}
//}


// get mapping used for testing purpose

//@GetMapping("/getCourse")
//public ResponseType getCourse() {
//	boolean status =false;
//	String message = "No Data Found";
//	List<CourseReport> course=null;
////	if(name!=null && name!="") {
//		course = service.getAllCountrys();
//		  if(course!=null && course.size()>0) {
//			 status =true;
//			 message="Data found";
//		  }
////	}
//		  logger.info(course);
//	ResponseType response = new ResponseType(200,message,status,course);
//	return response;
//}

//
//
//@GetMapping("/getCourse")
//public List<Report> getCourse() { 
////	List<Report> course=new ArrayList<>() ; 
////		course = service.getAllCountrys(); 
////	return repository.findAll();  
//	return null;
//}
//
//
//
//@GetMapping("/getCourses")
//public List<CourseReport> getReports() { 
////  return repository.findAll(); 
//	return null;
//}
//
//@GetMapping("/getCourseStatusReport")
//public List<CourseStatusReport> getCourseStatusReports() { 
////  return courseStatusrepository.findAll(); 
//	return null;
//	
//}
//
//@GetMapping("/getInstructorSubjectReport")
//public List<InstructorSubjectReport> getInstructorSubjectReports() { 
////  return instructorSubjectrepository.findAll();  
//	return null;
//}


//@GetMapping("/report/{fromDate}/{toDate}/{format}")
//public String generateReport(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable String format) throws FileNotFoundException, JRException {
// logger.info("from date"+fromDate);
//	
//	Date startDate=new Date(fromDate);
//  Date endDate=new Date(toDate);
//   
//	
//	return service.exportReport(format,startDate,endDate);
//}

