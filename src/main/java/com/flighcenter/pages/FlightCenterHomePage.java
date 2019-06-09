package com.flighcenter.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

/**
 * This is home page of flight center page when the URl is launched.
 * @author lakshman.shiva
 * @since 03-June-2019
 */
public class FlightCenterHomePage extends BasePage<FlightCenterHomePage> {

private static final int TIMEOUT = 5;
private static final String datediv = "div.trip-dates__depart-date-dialog>div>div>div";
private static final String date_buttons = "div.trip-dates__depart-date-dialog>div>div>div span";

@FindBy(css="input[type='radio'][value='oneway']")
WebElement onewaytrip;

@FindBy(css="input[type='radio'][value='return']")
WebElement returntrip;

@FindBy(css="input[type='Text'][id*='expoint']")
WebElement flyingfrom;

@FindBy(css="input[type='Text'][id*='destination']")
WebElement flyingto;

@FindBy(css="input[type='Text'][id*='departDate']")
WebElement departingdate;

@FindBy(css="input[type='Text'][id*='arriveDate']")
WebElement returndate;

@FindBy(css="input[type='Text'][id*='TicketClass']")
WebElement ticketclass;

@FindBy(css="div.undefined__button>button>div>div")
WebElement searchbtn;

@FindAll(@FindBy(how = How.CSS, using = "div[role='menu']>div"))
List<WebElement> alldestinations;

@FindBy(css="input[id*='departDate']")
WebElement departdate;

@FindBy(css= datediv)
WebElement datedialog;

@FindAll(@FindBy(how = How.CSS, using = "div.trip-dates__depart-date-dialog>div>div>div>div:nth-of-type(1)>div>div>div:nth-child(1)"))
List<WebElement> yeardiv;

@FindAll(@FindBy(how = How.CSS, using = "div.trip-dates__depart-date-dialog>div>div>div svg"))
List<WebElement> movemonth;

@FindAll(@FindBy(how = How.CSS, using = "div.trip-dates__depart-date-dialog>div>div>div span"))
List<WebElement> buttons_datedialog;

@FindBy(css="input[id*='arriveDate']")
WebElement arrivedate;


public FlightCenterHomePage(WebDriver driver)
{
	super(driver);	
	PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
}

@Override
protected void isLoaded() throws Error {
   
	try
	{		
	   this.wait.until(ExpectedConditions.visibilityOf(this.searchbtn));
	}
	catch(Exception e)
	{
		throw new Error("Issue in Loading Flight Center Home Page", e);
	}

	ExpectedCondition<Boolean> pageLoadCondition = new
            ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {                	
                    return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                }
            };
    wait.until(pageLoadCondition);
}

@Override
protected void load() {
	
	driver.get("https://www.flightcentre.co.nz/");			
}

/**
 * Flight Search based on different parameters * 
 */
public SearchResults searchFlight(boolean isoneway, String from, String to, String ticketclass, 
		String departdate, String retunrdate)
{
	if(isoneway)
		this.onewaytrip.click();	
	this.enterFlyFromTo(from, to);
	new Actions(this.driver).moveToElement(this.searchbtn).click(this.searchbtn).build().perform();
	return new SearchResults(this.driver).get();
}

/**
 * This method will enter fly from and to
 */
private void enterFlyFromTo(String flyfrom, String flyto)
{
   this.flyingfrom.click();
   this.flyingfrom.sendKeys(flyfrom);
   this.alldestinations.get(0).click();
   
   this.flyingto.click();
   this.flyingto.sendKeys(flyto);
   this.alldestinations.get(0).click();
 
	new Actions(this.driver).click(this.departdate).build().perform();
	wait.until(ExpectedConditions.visibilityOf(this.datedialog));
	this.enterDate("15", "", "2019");
	this.enterDate("30", "", "2020");
}

private void enterDate(SimpleDateFormat date)
{
	
	
}

/**
 * This method is used to enter Departing
 * and Return Date.
 * @param date
 */
private void enterDate(String date, String month, String year)
{  
	
	
	//Choose Year
	this.yeardiv.get(0).click();
    for(WebElement button : this.buttons_datedialog) {
    	
     if(button.getAttribute("innerHTML").contains(year))
     {
    	 button.click();
    	 wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(date_buttons), 20));
         break; 
     } 	
    	
    }
	
	//Choose Month
    
	
	
	//Choose Date    
    for(WebElement button : this.buttons_datedialog) {
    	
     if(button.getAttribute("innerHTML").contains(date))
     {   
    	 try {
    		 new Actions(this.driver).doubleClick(button).build().perform();
    		 this.wait.until(ExpectedConditions.invisibilityOf(this.datedialog));
             break; 
    	 }
    	 catch(Exception e)
    	 {
    		 new Actions(this.driver).doubleClick(button).build().perform();
    		 this.wait.until(ExpectedConditions.invisibilityOf(this.datedialog));
             break; 
    	 }
    	 
     } 	
    	
    }
	
}
	
}
