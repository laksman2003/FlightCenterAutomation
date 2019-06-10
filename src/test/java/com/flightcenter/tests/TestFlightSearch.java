package com.flightcenter.tests;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.flighcenter.pages.FlightCenterHomePage;
import com.flighcenter.pages.SearchResults;
import com.flighcenter.util.DataUtil;
import com.google.common.collect.Ordering;


public class TestFlightSearch {

public WebDriver driver;	
	
 @BeforeMethod
  public void driverSetup()
  {  
	 String path = System.getProperty("user.dir")+File.separator+"Binaries"+File.separator+"chromedriver.exe";
	 System.setProperty("webdriver.chrome.driver", path);
	 ChromeOptions opt = new ChromeOptions();
	 opt.addArguments("disable-infobars");
	 driver = new ChromeDriver(opt);
	 driver.manage().window().maximize();	 
  }
 	
  @Test
  public void searchFlight() throws FileNotFoundException, InterruptedException {  
	  
	  //Get Test Data
	  String flyfrom = null;
	  String flyto = null;
	  String departdate = null;
	  String returndate = null;
	  String ticketclass = null;
	  long loadtime = 0;
	  int adults = 0;
	  int children = 0;
	  int infants = 0;
	  
	  flyfrom = DataUtil.readCSVFile("Test001", "From");
	  flyto = DataUtil.readCSVFile("Test001", "To");
	  departdate = DataUtil.readCSVFile("Test001", "StartDate");
	  returndate = DataUtil.readCSVFile("Test001", "ReturnDate");
	  ticketclass = DataUtil.readCSVFile("Test001", "Class"); 
	  adults = Integer.parseInt(DataUtil.readCSVFile("Test001", "Adults"));
	  children = Integer.parseInt(DataUtil.readCSVFile("Test001", "Children"));
	  infants = Integer.parseInt(DataUtil.readCSVFile("Test001", "Infants"));
	  
	  //Launch App
	  FlightCenterHomePage homepage = new FlightCenterHomePage(driver).get();
	  
	  //Search Flights
	  SearchResults resultpage = homepage.
			  searchFlight(false, flyfrom, flyto, ticketclass, departdate, returndate, adults, children, infants);	
	  
	  //Search Results Load time
	  loadtime = FlightCenterHomePage.timetoload;
	  
	  //Get price list
	  List<Double>costlist = resultpage.getFlightCost();
	  
	  //Validate cost is sorted in increasin order and repsonse time within 3.5 seconds for flight search
	  boolean issorted = Ordering.natural().isOrdered(costlist);
	  boolean isloadtime = loadtime <= 3500;
	  Assert.assertEquals(issorted, true);	 
	  Assert.assertEquals(isloadtime, true);	  
  }
  
  @AfterMethod
  public void cleanup()
  { 
	 driver.close();
	 driver.quit();
  }
}
