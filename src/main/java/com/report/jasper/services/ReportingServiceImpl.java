package com.report.jasper.services;

import java.io.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.report.jasper.controller.ReportController;
import com.report.jasper.entities.CourseReport;
import com.report.jasper.entities.CourseStatusReport;
import com.report.jasper.entities.InstructorSubjectReport;
import com.report.jasper.entities.MultiCoursesReport;
import com.report.jasper.entities.Report;
import com.report.jasper.entities.Report2;
import com.report.jasper.entities.Report3;
import com.report.jasper.repository.CourseStatusReportRepository;
import com.report.jasper.repository.InstructorSubjectReportRepository;
import com.report.jasper.repository.MultiCoursesReportRepository;
import com.report.jasper.repository.ReportRepository; 
import net.sf.jasperreports.engine.JRException; 
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign; 
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter; 
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class ReportingServiceImpl implements ReportingService {

	@Autowired
	private ReportRepository repository;
	@Autowired	
	private CourseStatusReportRepository courseStatusrepository;
	@Autowired
	private InstructorSubjectReportRepository instructorSubjectrepository;
	@Autowired
	private MultiCoursesReportRepository multiCoursesReportRepository;
	
	@Value("${file.upload-dir}")
    private String folderLocation;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	public String exportReport(String reportFormat,CourseReport courseDetailsData) throws FileNotFoundException, JRException {
//		String path = "C:\\Users\\ibrahim.fazil\\Desktop\\Reportss"; 
		String path = folderLocation; 
		List<CourseReport> reports= null;
		String result=null;
		try {  
				reports = repository.generateCourseDetailsReport(courseDetailsData.getCourseName(),
	    		courseDetailsData.getDuration(), courseDetailsData.getActivationId(),
	    		courseDetailsData.getStartDate(), courseDetailsData.getEndDate(),
	    		courseDetailsData.getCategory(), courseDetailsData.getHall(), 
	    		courseDetailsData.getLocationName(), courseDetailsData.getInstructor());
				
				logger.info("report Format"+ reportFormat);
				logger.info("report size"+ reports);
				
				if(reports!=null && reports.size()>0){
					if(reportFormat!=null && !reportFormat.trim().equals("")){
						reports.add(0, null);  
						return result =	generatePdfOrExcelReport(  reportFormat,  path, "/CourseDetailsReport",  reports);
					}
				}else{
					return result= "No Data Found";
				}	
				 
	} catch (Exception e) { 
		e.printStackTrace();
		result="Exception Occured While generating Report : Exception";
		return result;
	}
		return result="Report generated Successfully"; 
	}
	
	
	
	
	public String exportCourseStatusReport(String reportFormat,CourseStatusReport courseStatusData) throws FileNotFoundException, JRException {
//		String path = "C:\\Users\\ibrahim.fazil\\Desktop\\Reportss";
		String path = folderLocation; 
		List<CourseStatusReport> reports= null;
		String result="";
		try {  
				reports = courseStatusrepository.generateCourseStatusReport(courseStatusData.getEmployee(),
				courseStatusData.getJobNo(),courseStatusData.getCourseName(), 
				courseStatusData.getStartDate(),courseStatusData.getEndDate(), 
				courseStatusData.getDuration(),
				courseStatusData.getJob(),
				courseStatusData.getJobTitle(),courseStatusData.getDepartment(), 
				courseStatusData.getJobCardNo(),
				courseStatusData.getRequestStatus(),
				courseStatusData.getCourseStatus());
				
				for(CourseStatusReport report:reports ) {
					logger.info("request status list :** "+report.getJobCardNo()+"'"+ report.getRequestStatus());
				}
				
				logger.info("report Format"+ reportFormat);
				logger.info("report size"+ reports);
				
				if(reports!=null && reports.size()>0){
					if(reportFormat!=null && !reportFormat.trim().equals("")){
						reports.add(0, null);  
						return result =	generatePdfOrExcelReport(  reportFormat,  path, "/CourseStatusReport",  reports);
					}
				}else{
					return result= "No Data Found";
				}	
				 
	} catch (Exception e) { 
		e.printStackTrace();
		result="Exception Occured While generating Report : Exception";
		return result; 
	}
		return result = "Report generated Successfully"; 
	}
	
	
	
	
	public String  exportInstructorSubjectReport(String reportFormat,InstructorSubjectReport instSubjReport) throws FileNotFoundException, JRException {
//		String path = "C:\\Users\\ibrahim.fazil\\Desktop\\Reportss";
		String path = folderLocation; 
		String result="";
		List<InstructorSubjectReport> reports= null;
		try {  
				reports = instructorSubjectrepository.generateInstructorReport(instSubjReport.getName(),
				instSubjReport.getType(),instSubjReport.getJobId(),
				instSubjReport.getOrganisation(),instSubjReport.getJobTitle(),
				instSubjReport.getCompanyName(),instSubjReport.getQid(),
				instSubjReport.getCourse(),instSubjReport.getPriority());  
				
				logger.info("report Format"+ reportFormat);
				logger.info("report size"+ reports);
				
				
				if(reports!=null && reports.size()>0){
					if(reportFormat!=null && !reportFormat.trim().equals("")){ 
						reports.add(0, null);  
						return result =	generatePdfOrExcelReport(  reportFormat,  path, "/InstructorSubjectReport",  reports);
					}
				}else{
					return result= "No Data Found";
				}		
		 
	} catch (Exception e) { 
		e.printStackTrace();
		result="Exception Occured While generating Report : Exception"; 
	}
		return result= "Report generated Successfully"; 
	}
	
	
	public String  exportMultiCoursesReport(String reportFormat,MultiCoursesReport multiCoursesReport) throws FileNotFoundException, JRException {
//		String path = "C:\\Users\\ibrahim.fazil\\Desktop\\Reportss";
		String path = folderLocation; 
		String result="";
		List<MultiCoursesReport> reports= null;
		try { 
				reports = multiCoursesReportRepository.generateMultiCoursesReport(multiCoursesReport.getCourseName(),
						  multiCoursesReport.getStartDate(),multiCoursesReport.getEndDate(),
					      multiCoursesReport.getCourseCoordinator(),multiCoursesReport.getInstructor(),
						  multiCoursesReport.getLocationName(),multiCoursesReport.getOrganisation()); 
				
		logger.info("report Format"+ reportFormat);
		logger.info("report size"+ reports);
	
		if(reports!=null && reports.size()>0){
			if(reportFormat!=null && !reportFormat.trim().equals("")){
				reports.add(0, null);  
				return result =	generatePdfOrExcelReport(  reportFormat,  path,  "/MultiCoursesReport",  reports);
			}
		}else{
			return result= "No Data Found";
		}
		
	} catch (Exception e) { 
		e.printStackTrace();
		result="Exception Occured While generating Report : Exception"; 
	}
		return result= "Report generated Successfully"; 
	}


	
	
	 public String generatePdfOrExcelReport(String reportFormat,String path,String filename,Object reports) {
		 String result="";
		 try { 
				    final InputStream reportInputStream = getClass().getResourceAsStream("/reports"+filename+".jrxml");
					final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);
					JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
					JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource((Collection<?>) reports);
					Map<String, Object> parameters = new HashMap<>();
					parameters.put("CollectionBeanParam", jrDataSource);
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
			
					if (reportFormat.equalsIgnoreCase("html")) {
						JasperExportManager.exportReportToHtmlFile(jasperPrint, path+filename+".html");
					} else if (reportFormat.equalsIgnoreCase("pdf")) {
						JasperExportManager.exportReportToPdfFile(jasperPrint, path+filename+".pdf");
					} else if (reportFormat.equalsIgnoreCase("xls")) {
						SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
						configuration.setOnePagePerSheet(true);
						configuration.setIgnoreGraphics(false); 
						File outputFile = new File( path+filename+".xlsx");
						ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
						OutputStream fileOutputStream = new FileOutputStream(outputFile);
						Exporter exporter = new JRXlsxExporter();
						exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
						exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
						exporter.setConfiguration(configuration);
						exporter.exportReport();
						try {
							byteArrayOutputStream.writeTo(fileOutputStream);
						} catch (IOException e) { 
							e.printStackTrace();
							result="Exception Occured While generating Report :IO Exception";
							return result; 
						}
					} 
		 }catch(Exception ex) {
//			 ex.printStackTrace();
			  result= "Error while Report Generation :"+result ; 
		 }
		 return result;
	 }




	@Override
	public List<CourseReport> getCourseReportData() { 
		return null;
	}




	@Override
	public List<CourseStatusReport> getCourseStatusReportData() { 
		return null;
	}




	@Override
	public List<InstructorSubjectReport> getInstructSubjReportData() { 
		return null;
	}
	
	
	///Testing With List
	
//	@Override
//	public List<Report> getAllCountrys() {
//		// TODO Auto-generated method stub
//
//		List<Report> reportList = new ArrayList<Report>();
////		for(int i=1;i<5;i++) {
//		
//			Report report =new Report(); report.setId(1l);
//		  report.setCourseName("test1"); report.setDuration(1l); 
//		  report.setActivationId(1l); report.setCategory("test");
//		  report.setStartDate(new Date());report.setEndDate(new Date());
//		  report.setHall("new halls"); report.setInstructor("tester");
//		  report.setLocationName("qattest");
//		  
//		  
//		  Report report2 =new Report(); report2.setId(2l);
//		  report2.setCourseName("test2"); report2.setDuration(1l); 
//		  report2.setActivationId(1l); report2.setCategory("test");
//		  report2.setStartDate(new Date());report2.setEndDate(new Date());
//		  report2.setHall("new halls"); report2.setInstructor("tester");
//		  report2.setLocationName("qattest");
//		  
//		  
//		  reportList.add(report);
//		  reportList.add(report2);
//		  reportList.add(0,null);
//		  
////			}
//			logger.info(reportList);
////			return repository.findAll();
//			return reportList;
//		}
//		  
//	@Override
//	public List<Report2> getAllCountrys2() {
//		  List<Report2> reportList2 = new ArrayList<Report2>();
//		  Report2 report3 =new Report2(); report3.setCourseStatus("active");
//		  report3.setCourseName("test1"); report3.setDuration(1l); 
//		  report3.setEmployee("employee"); report3.setJob("JOB"); 
//		  report3.setStartDate(new Date());report3.setEndDate(new Date());
//		  report3.setJobTitle("developer"); report3.setRequestStatus("activated"); 
//		  report3.setJobCardNo(111l);
//		  
//		  Report2 report4 =new Report2(); report4.setCourseStatus("active");
//		  report4.setCourseName("test1"); report4.setDuration(1l); 
//		  report4.setEmployee("employee"); report4.setJob("JOB"); 
//		  report4.setStartDate(new Date());report4.setEndDate(new Date());
//		  report4.setJobTitle("developer"); report4.setRequestStatus("activated"); 
//		  report4.setJobCardNo(111l);
//		  
//		  
//		  reportList2.add(report3);
//		  reportList2.add(report4);
//		  reportList2.add(0,null);
//		  
////			}
//			logger.info(reportList2);
////			return repository.findAll();
//			return reportList2;
//		}
//		  
//	@Override
//	public List<Report3> getAllCountrys3() {
//		  List<Report3> reportList3 = new ArrayList<Report3>();
//		  Report3 report6 =new Report3(); report6.setQid("21312321");
//		  report6.setCourseName("test1"); report6.setJobTitle("tester");  
//		  report6.setName("trestre");   report6.setOrganisation("resewwww"); 
//		  report6.setPriority("frst");  report6.setType("pdf"); 
//		  report6.setCourse("JSPS"); report6.setJobId("123");
//		  report6.setCompanyName("NEW one");
//		  
//		  
//		  Report3 report7 =new Report3();  report7.setQid("21312321");
//		  report7.setCourseName("test1"); report7.setJobTitle("tester");  
//		  report7.setName("trestre");   report7.setOrganisation("resewwww"); 
//		  report7.setPriority("frst");  report7.setType("pdf"); 
//		  
// 
//	
//		  reportList3.add(report6);
//		  reportList3.add(report7);
//		  reportList3.add(0,null);
////			}
//			logger.info(reportList3);
////			return repository.findAll();
//			return reportList3;
//		}
//

	
	// Testing path and other references while testing using LIst

	//in mac  String path = "/Users/ibrahim/Documents/Reports/"; 
//			List<CourseReport> reports = repository.findAll(); 
//			Date fromDate=new Date("22-MAY-19");
//			Date toDate=new Date("23-MAY-19");  
//			List<CourseReport> reports = repository.generateReportByDates(fromDate,toDate); 
//		    List<Report> reports = getAllCountrys(); 
		    
					
					
					
			
					
//					logger.info("report Format"+ reportFormat);
//					logger.info("report size"+ reports);
//					for(CourseReport report:reports) {
//						logger.info("repor cpourse name "+ report.getCourseName());
//					}
//					logger.info("report size 2***"+ reports.size());
////			if((reportFormat!=null && !reportFormat.trim().contains(""))
////					&& (reports!=null && reports.size()>0)) {
//					if((reportFormat!=null && !reportFormat.trim().equals("") )
//							&& (reports!=null && reports.size()>0 )) {
////			load file and compile it
////		    File file = ResourceUtils.getFile("classpath:reports/CourseReport_A4.jrxml");CourseReport_A4
////		    final InputStream reportInputStream = getClass().getResourceAsStream("/reports/CountryReport.jrxml");
//			reports.add(0,  new  CourseReport());
//			logger.info("report size 2***"+ reports.size());
//			final InputStream reportInputStream = getClass().getResourceAsStream("/reports/CourseDetailsReport.jrxml");
//			final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);
	//
////		    return JasperCompileManager.compileReport(jasperDesign);
////		    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	//
//			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//			JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(reports);
//			Map<String, Object> parameters = new HashMap<>();
//			parameters.put("CollectionBeanParam", jrDataSource);
//			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
	//
//			if (reportFormat.equalsIgnoreCase("html")) {
//				JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "/CourseDetailsReport.html");
//			} else if (reportFormat.equalsIgnoreCase("pdf")) {
//				JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/CourseDetailsReport.pdf");
//			} else if (reportFormat.equalsIgnoreCase("xls")) {
//				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
//				configuration.setOnePagePerSheet(true);
//				configuration.setIgnoreGraphics(false);
	////in mac	File outputFile = new File("/Users/ibrahim/Documents/Reports/Report.xlsx");
//				File outputFile = new File( path + "/CourseDetailsReport.xlsx");
//				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//				OutputStream fileOutputStream = new FileOutputStream(outputFile);
//				Exporter exporter = new JRXlsxExporter();
//				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
//				exporter.setConfiguration(configuration);
//				exporter.exportReport();
//				try {
//					byteArrayOutputStream.writeTo(fileOutputStream);
//				} catch (IOException e) { 
//					e.printStackTrace();
//					result="Exception Occured While generating Report : Exception";
//					return result;
//				}
//			}
//			}else {
//				return result= "No Data Found";
//			}
	

}
