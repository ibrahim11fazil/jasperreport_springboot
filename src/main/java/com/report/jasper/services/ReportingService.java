package com.report.jasper.services;
 

import java.io.FileNotFoundException;
import java.util.List;

import net.sf.jasperreports.engine.JRException;


public interface ReportingService {
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException;

	List getAllCountrys();
}
