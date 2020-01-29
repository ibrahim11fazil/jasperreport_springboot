package com.report.jasper.services;
 
import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.report.jasper.entities.CourseReport;
import com.report.jasper.entities.Report;
import com.report.jasper.repository.ReportRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration; 


@Service
public class ReportingServiceImpl implements ReportingService {
	
	@Autowired
    private ReportRepository repository;
	
	@Override
	public List<CourseReport> getAllCountrys() {
		// TODO Auto-generated method stub
		

		List<CourseReport> reportList =new ArrayList<CourseReport>();
//		for(int i=1;i<5;i++) {
		/*
		 * CourseReport report =new CourseReport(); report.setId(i++);
		 * report.setCourseName("test"+i); report.setDuration(i++);
		 * report.setNoOfHours(i++); report.setUserCreated("REPORT "+i);
		 */
		
		/*
		 * CourseReport report1 =new CourseReport(); report1.setId(1);
		 * report1.setCourseName("test 1" ); report1.setDuration(1);
		 * report1.setNoOfHours(1); report1.setUserCreated("REPORT 1" ); CourseReport
		 * report2 =new CourseReport(); report2.setId(2); report2.setCourseName("test2"
		 * ); report2.setDuration(2); report2.setNoOfHours(2);
		 * report2.setUserCreated("REPORT 2 " );
		 * 
		 * CourseReport report3 =new CourseReport(); report3.setId(3);
		 * report3.setCourseName("test 3" ); report3.setDuration(3);
		 * report3.setNoOfHours(3); report3.setUserCreated("REPORT 3" );
		 * 
		 * CourseReport report4 =new CourseReport(); report4.setId(3);
		 * report4.setCourseName("test 3" ); report4.setDuration(3);
		 * report4.setNoOfHours(3); report4.setUserCreated("REPORT 3" );
		 * 
		 * CourseReport report5 =new CourseReport(); report5.setId(3);
		 * report5.setCourseName("test 3" ); report5.setDuration(3);
		 * report5.setNoOfHours(3); report5.setUserCreated("REPORT 3" );
		 * reportList.add(report1); reportList.add(report2); reportList.add(report3);
		 * reportList.add(report4); reportList.add(report5);
		 */
			
			
			
//		}
	System.out.println(reportList);
//		return repository.findAll();
		return reportList;
	}


    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
//	        String path = "/Users/ibrahim/Documents/Reports/";
    	  String path = "C:\\Users\\ibrahim.fazil\\Desktop\\Reportss";
	      List<CourseReport> reports = repository.findAll();
//	        List<CourseReport> reports = getAllCountrys(); 
	      //      load file and compile it
//	      	File file = ResourceUtils.getFile("classpath:reports/CourseReport_A4.jrxml");
//	        final InputStream reportInputStream = getClass().getResourceAsStream("/reports/CountryReport.jrxml");
	      
	        final InputStream reportInputStream = getClass().getResourceAsStream("/reports/CourseReport_A4.jrxml");
	        final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);
	      
	      
//	     	 return JasperCompileManager.compileReport(jasperDesign);
//	     	 JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        
	        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	        JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(reports);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("CollectionBeanParam", jrDataSource);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
	        
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "/reports.html");
        }
        else if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/reports.pdf");
        }
        else if (reportFormat.equalsIgnoreCase("xls")) {
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setOnePagePerSheet(true);
			configuration.setIgnoreGraphics(false);
//			File outputFile = new File("/Users/ibrahim/Documents/Reports/Report.xlsx");
			File outputFile = new File("C:/Users/ibrahim.fazil/Desktop/Reportss/Report.xlsx");
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
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

        }

        return "report generated in path : " + path;
    }
}

 