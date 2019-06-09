package com.flighcenter.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.univocity.parsers.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.*;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

/**
 * Util Class to get Test Data from CSV file
 * @author Laksh
 *
 */
public class DataUtil {
	
	/**
	 * This method will get the value of particular Test case Id(row) and column name (column)
	 * @param testcaseid
	 * @param columnnname
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static String readCSVFile(String testcaseid, String columnname) throws FileNotFoundException
	{
		String value = "";
		String datapath = System.getProperty("user.dir")+File.separator+"TestData"+File.separator+"flightsearch.csv";
		FileReader reader = new FileReader(datapath);
		
		CsvParserSettings settings = new CsvParserSettings();
		settings.getFormat().setLineSeparator("\n");		
		settings.selectFields("TestCaseId", columnname);
		CsvParser parser = new CsvParser(settings);
		List<String[]> rows = parser.parseAll(reader);
		
        for (String[] row: rows)
        {   
        	for(String columnvalue : row)
        	{
        		if(columnvalue.equals(testcaseid))
        		{
        			value = row[1];
        			break;
        		}
        	}
        }
		
		return value;
		
	}
	

}
