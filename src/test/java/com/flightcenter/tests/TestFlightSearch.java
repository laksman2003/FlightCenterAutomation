package com.flightcenter.tests;

import org.testng.annotations.Test;
import java.io.File;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import com.flighcenter.pages.FlightCenterHomePage;
import com.flighcenter.pages.SearchResults;
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
  public void searchFlight() {  
	  
	  FlightCenterHomePage homepage = new FlightCenterHomePage(driver).get();
	  SearchResults resultpage = homepage.searchFlight(false, "Chennai", "Auckland", "", "", "");	
	  List<Double>costlist = resultpage.getFlightCost();
	  boolean issorted = Ordering.natural().isOrdered(costlist);
	  Assert.assertEquals(issorted, true);
	  System.out.println("Search Completed");
  }
}
