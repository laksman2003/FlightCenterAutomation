package com.flightcenter.tests;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
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
  public void searchFlight() throws FileNotFoundException {  
	  
	  //Get Test Data
	  String flyfrom = null;
	  String flyto = null;
	  flyfrom = DataUtil.readCSVFile("Test001", "From");
	  flyto = DataUtil.readCSVFile("Test001", "To");
	  
	  //Launch App
	  FlightCenterHomePage homepage = new FlightCenterHomePage(driver).get();
	  
	  //Search Flights
	  SearchResults resultpage = homepage.searchFlight(false, flyfrom, flyto, "", "", "");	
	  
	  //Get price list
	  List<Double>costlist = resultpage.getFlightCost();
	  
	  //Validate
	  boolean issorted = Ordering.natural().isOrdered(costlist);
	  Assert.assertEquals(issorted, true);
	  System.out.println("Search Completed");
  }
}
