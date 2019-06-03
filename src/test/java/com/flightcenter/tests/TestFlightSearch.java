package com.flightcenter.tests;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.flighcenter.pages.FlightCenterHomePage;

public class TestFlightSearch {

public WebDriver driver;	
	
 @BeforeTest
  public void setup()
  {  
	 String path = System.getProperty("user.dir")+File.separator+"Binaries"+File.separator+"chromedriver.exe";
	 System.setProperty("webdriver.chrome.driver", path);
	 driver = new ChromeDriver();	
	 driver.get("https://www.flightcentre.co.nz/");
  }
 	
  @Test
  public void searchFlight() {  
	  
	  FlightCenterHomePage homepage = new FlightCenterHomePage(driver);
	  homepage.searchFlight(false, "Chennai", "Auckland", "", "", "");	  
  }
}
