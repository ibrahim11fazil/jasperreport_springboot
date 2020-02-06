package com.report.jasper.services;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.report.jasper.entities.CourseReport;
import com.report.jasper.entities.CourseStatusReport;
import com.report.jasper.entities.InstructorSubjectReport;
import com.report.jasper.entities.Report;
import com.report.jasper.entities.Report2;
import com.report.jasper.entities.Report3;
import com.report.jasper.repository.CourseStatusReportRepository;
import com.report.jasper.repository.InstructorSubjectReportRepository;
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
	
	@Value("${file.upload-dir}")
    private String folderLocation;
	 
	
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
//			System.out.println(reportList);
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
//			System.out.println(reportList2);
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
//			System.out.println(reportList3);
////			return repository.findAll();
//			return reportList3;
//		}
//


	public String exportReport(String reportFormat,CourseReport courseDetailsData) throws FileNotFoundException, JRException {
//		String path = "C:\\Users\\ibrahim.fazil\\Desktop\\Reportss";
		
		String path = folderLocation + "/" ;
		List<CourseReport> reports= null;
		String result=null;
		try { 
				reports=new ArrayList<CourseReport>();
//in mac  String path = "/Users/ibrahim/Documents/Reports/";
	
		
//		List<CourseReport> reports = repository.findAll(); 
//		Date fromDate=new Date("22-MAY-19");
//		Date toDate=new Date("23-MAY-19"); 
			
//		List<CourseReport> reports = repository.generateReportByDates(fromDate,toDate); 
//	    List<Report> reports = getAllCountrys(); 
	    
	    
				reports = repository.generateCourseDetailsReport(courseDetailsData.getCourseName(),
	    		courseDetailsData.getDuration(), courseDetailsData.getActivationId(),
	    		courseDetailsData.getStartDate(), courseDetailsData.getEndDate(),
	    		courseDetailsData.getCategory(), courseDetailsData.getHall(), 
	    		courseDetailsData.getLocationName(), courseDetailsData.getInstructor());
				System.out.println("report Format"+ reportFormat);
				System.out.println("report size"+ reports);
				for(CourseReport report:reports) {
					System.out.println("repor cpourse name "+ report.getCourseName());
				}
				System.out.println("report size 2***"+ reports.size());
//		if((reportFormat!=null && !reportFormat.trim().contains(""))
//				&& (reports!=null && reports.size()>0)) {
				if((reportFormat!=null && !reportFormat.trim().equals("") )
						&& (reports!=null && reports.size()>0 )) {
//		load file and compile it
//	    File file = ResourceUtils.getFile("classpath:reports/CourseReport_A4.jrxml");CourseReport_A4
//	    final InputStream reportInputStream = getClass().getResourceAsStream("/reports/CountryReport.jrxml");
		reports.add(0,  new  CourseReport());
		System.out.println("report size 2***"+ reports.size());
		final InputStream reportInputStream = getClass().getResourceAsStream("/reports/CourseDetails_A4.jrxml");
		final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

//	    return JasperCompileManager.compileReport(jasperDesign);
//	    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(reports);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("CollectionBeanParam", jrDataSource);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);

		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "/CourseDetailsReport.html");
		} else if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/CourseDetailsReport.pdf");
		} else if (reportFormat.equalsIgnoreCase("xls")) {
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setOnePagePerSheet(true);
			configuration.setIgnoreGraphics(false);
//in mac	File outputFile = new File("/Users/ibrahim/Documents/Reports/Report.xlsx");
			File outputFile = new File("C:/Users/ibrahim.fazil/Desktop/Reportss/CourseDetailsReport.xlsx");
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
				result="Exception Occured While generating Report : Exception";
				return result;
			}
		}
		}else {
			return result= "No Data Found";
		}
		
	} catch (Exception e) { 
		e.printStackTrace();
		result="Exception Occured While generating Report : Exception";
		return result;
	}
		return result="Report generated Successfully";
//		return reports;
	}
	
	
	
	
	public String exportCourseStatusReport(String reportFormat,CourseStatusReport courseStatusData) throws FileNotFoundException, JRException {
//		String path = "C:\\Users\\ibrahim.fazil\\Desktop\\Reportss";
		String path = folderLocation + "/" ;
		List<CourseStatusReport> reports= null;
		String result="";
		try { 
				reports=new ArrayList<CourseStatusReport>(); 
				reports = courseStatusrepository.generateCourseStatusReport(courseStatusData.getEmployee(),
				courseStatusData.getJobNo(),courseStatusData.getCourseName(), 
				courseStatusData.getStartDate(),courseStatusData.getEndDate(), 
				courseStatusData.getDuration(),
				courseStatusData.getJob(),
				courseStatusData.getJobTitle(),courseStatusData.getDepartment(), 
				courseStatusData.getJobCardNo(),
				courseStatusData.getRequestStatus(),
				courseStatusData.getCourseStatus());
				
				
		if((reportFormat!=null && !reportFormat.trim().equals(""))
						&& (reports!=null && reports.size()>0)) { 
		reports.add(0, null);
		final InputStream reportInputStream = getClass().getResourceAsStream("/reports/CourseStatus_A4.jrxml");
		final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(reports);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("CollectionBeanParam", jrDataSource);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);

		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "/CourseStatusReport.html");
		} else if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/CourseStatusReport.pdf");
		} else if (reportFormat.equalsIgnoreCase("xls")) {
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setOnePagePerSheet(true);
			configuration.setIgnoreGraphics(false); 
			File outputFile = new File("C:/Users/ibrahim.fazil/Desktop/Reportss/CourseStatusReport.xlsx");
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
		}else {
			return result= "No Data Found";
		}
		
	} catch (Exception e) { 
		e.printStackTrace();
		result="Exception Occured While generating Report : Exception";
		return result; 
	}
		return result = "Report generated Successfully";
//		return reports;
	}
	
	
	
	
	public String  exportIntructorSubjectReport(String reportFormat,InstructorSubjectReport instSubjReport) throws FileNotFoundException, JRException {
//		String path = "C:\\Users\\ibrahim.fazil\\Desktop\\Reportss";
		String path = folderLocation + "/" ;
		String result="";
		List<InstructorSubjectReport> reports= null;
		try { 
//				reports=new ArrayList<InstructorSubjectReport>();
				reports = instructorSubjectrepository.generateInstructorReport(instSubjReport.getName(),
				instSubjReport.getType(),instSubjReport.getJobId(),
				instSubjReport.getOrganisation(),instSubjReport.getJobTitle(),
				instSubjReport.getCompanyName(),instSubjReport.getQid(),
				instSubjReport.getCourse(),instSubjReport.getPriority()); 
//	   			List<Report3> reports = getAllCountrys3(); 
				

//				if(reports!=null && reports.size()>0) {
//					reports = getInstructSubjReportData(); 
//				}else {
//					result ="No Data Found"; 
//				} 
		System.out.println("report Format"+ reportFormat);
		System.out.println("report size"+ reports);
		if((reportFormat!=null && !reportFormat.trim().equals(""))
				&& (reports!=null && reports.size()>0)) {
					reports.add(0, null);
		    final InputStream reportInputStream = getClass().getResourceAsStream("/reports/InstructorSubject_A4.jrxml");
			final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(reports);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("CollectionBeanParam", jrDataSource);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
	
			if (reportFormat.equalsIgnoreCase("html")) {
				JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "/InstructorSubjectReport.html");
			} else if (reportFormat.equalsIgnoreCase("pdf")) {
				JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/InstructorSubjectReport.pdf");
			} else if (reportFormat.equalsIgnoreCase("xls")) {
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
				configuration.setOnePagePerSheet(true);
				configuration.setIgnoreGraphics(false); 
				File outputFile = new File("C:/Users/ibrahim.fazil/Desktop/Reportss/InstructorSubjectReport.xlsx");
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

		}else {
			return result= "No Data Found";
		}
		
	} catch (Exception e) { 
		e.printStackTrace();
		result="Exception Occured While generating Report : Exception";
//		return result;
	}
		return result= "Report generated Successfully";
//		return reports;
	}

	 public String printPDFExcelRepor(String result) {
		 result="";
		 try {
			 
			 
		 }catch(Exception ex) {
//			 ex.printStackTrace();
			  result= "Error while Printing :"+result ; 
		 }
		 return result;
	 }




	@Override
	public List<CourseReport> getCourseReportData() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<CourseStatusReport> getCourseStatusReportData() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<InstructorSubjectReport> getInstructSubjReportData() {
		// TODO Auto-generated method stub
		return null;
	}
}
