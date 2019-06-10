package com.flighcenter.pages;

import java.time.Month;
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
private static final String depratdate_dialog = "div.trip-dates__depart-date-dialog>div>div>div";
private static final String returndate_dialog = "div.trip-dates__return-date-dialog>div>div>div";
private static final String datedialog_year = "div:nth-of-type(1)>div>div>div:nth-child(1)";
public static long timetoload = 0;

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

@FindBy(css="input[id*='arriveDate']")
WebElement arrivedate;

@FindBy(css= depratdate_dialog)
WebElement departdate_window;

@FindBy(css= returndate_dialog)
WebElement returndate_window;

@FindBy(css="div[direction='up']>div[style*='height']")
WebElement monthdiv;

@FindBy(css="input[id*='TicketClass']")
WebElement ticketbutton;

@FindBy(css="div.passengers__popover>div:nth-child(1)>div>div")
WebElement passengerpopup;

@FindBy(css="div[class*='selection__adults-incrementor'] div.incrementor__iterators button[class*='remove']")
WebElement removeadult;

@FindBy(css="div[class*='selection__adults-incrementor'] div.incrementor__iterators button[class*='add']")
WebElement addadult;

@FindBy(css="div[class*='selection__adults-incrementor'] div.incrementor__iterators span.incrementor__name")
WebElement adultvalue;

@FindBy(css="div[class*='selection__children-incrementor'] div.incrementor__iterators button[class*='remove']")
WebElement removechildren;

@FindBy(css="div[class*='selection__children-incrementor'] div.incrementor__iterators button[class*='add']")
WebElement addchildren;

@FindBy(css="div[class*='selection__children-incrementor'] div.incrementor__iterators span.incrementor__name")
WebElement chilndrenvalue;

@FindBy(css="div[class*='selection__infants-incrementor'] div.incrementor__iterators button[class*='remove']")
WebElement removeinfants;

@FindBy(css="div[class*='selection__infants-incrementor'] div.incrementor__iterators button[class*='add']")
WebElement addinfants;

@FindBy(css="div[class*='selection__infants-incrementor'] div.incrementor__iterators span.incrementor__name")
WebElement infantsvalue;

@FindBy(css="div[id^='fareType'] button")
WebElement buttonfaretype;

@FindBy(css="span[role='menuitem']>div>div>div")
List<WebElement> menuitems;

@FindBy(css="div.passengers__popover>div:nth-child(1)>div>div button")
List<WebElement> okbutton;


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
 * Flight Search based on different parameters
 * @throws InterruptedException 
 */
public SearchResults searchFlight(boolean isoneway, String from, String to, String ticketclass, 
		String departdate, String retunrdate, int adults, int children, int infants) throws InterruptedException
{  
	//Select Trip Type
	if(isoneway)
		this.onewaytrip.click();	
	
	//Enter Fly from and Fly to
	this.enterFlyFromTo(from, to);
	
	//Enter Travel Dates
	this.enterDate(departdate, retunrdate, isoneway);
	
	//Passenger info
	this.enterPassengerDetails(adults, children, infants);
	
	//Choose Ticket Class
	this.chooseTicketClass(ticketclass);
	
	//Perform Search
	//new Actions(this.driver).moveToElement(this.searchbtn).click(this.searchbtn).build().perform();
	((JavascriptExecutor)this.driver).executeScript("arguments[0].click()", this.searchbtn);
	
	long start = System.currentTimeMillis();
	SearchResults searchresults =  new SearchResults(this.driver).get();
	long finish = System.currentTimeMillis();
	timetoload = finish - start; 
	return searchresults;
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
}

/**
 * Method to Select the Depart and Return
 * @param date -- String should be in format DD-MONTH-Year
 */
private void enterDate(String departdate, String returndate,  Boolean isonewaytrip)
{  
	
	String[] arrdepartdate = departdate.split("-");
	String[] arrreturndate = returndate.split("-");
	this.enterDate(arrdepartdate[0], arrdepartdate[1], arrdepartdate[2], "depart");	
	
	if(isonewaytrip)
	{
		List<WebElement> buttons = this.driver.findElement(By.cssSelector(returndate_dialog)).
		findElements(By.cssSelector("span"));
		
		for(WebElement button : buttons)
		{
			if(button.getAttribute("innerHTML").equalsIgnoreCase("cancel"))
			{
				button.click();
				break;
			}
		}					
	}
	else
	{
		this.enterDate(arrreturndate[0], arrreturndate[1], arrreturndate[2], "return") ;
	}
	
}

/**
 * This method is used to enter Dates.
 * and Return Date.
 * @param date, month and year in String
 */
private void enterDate(String date, String month, String year, String triptype)
{  
	String dateselector = null;
	
	//open date pop up
	if(triptype.toLowerCase().contains("depart"))
    {
    	new Actions(this.driver).click(this.departdate).build().perform();
    	wait.until(ExpectedConditions.visibilityOf(this.departdate_window));    	
    	dateselector = depratdate_dialog;
    }	
	else
	{   
		if(!this.returndate_window.isDisplayed())
		{
			 new Actions(this.driver).click(this.arrivedate).build().perform();
	    	 wait.until(ExpectedConditions.visibilityOf(this.returndate_window));    	
		}		
    	dateselector = returndate_dialog;
	}
	
	//Choose Year
	WebElement datewindow = this.driver.findElement(By.cssSelector(dateselector));
	this.driver.findElements(By.cssSelector(dateselector+">"+datedialog_year)).get(0).click();
	datewindow = this.driver.findElement(By.cssSelector(dateselector));
	List<WebElement>buttons_datedialog = datewindow.findElements(By.cssSelector("span"));	
    for(WebElement button : buttons_datedialog) 
    {    	
     if(button.getAttribute("innerHTML").contains(year))
     {
    	 button.click();
    	 wait.until(ExpectedConditions.
    			 numberOfElementsToBeMoreThan(By.cssSelector(dateselector+" "+"span"), 20));
         break; 
     }
    }
	
	//Choose Month
    int ticketmonth = Month.valueOf(month.toUpperCase()).getValue();
    String displayedmonth = this.monthdiv.getAttribute("innerHTML").split(" ")[0];
    int intdisplayedmonth = Month.valueOf(displayedmonth.toUpperCase()).getValue();
    int monthdiff = intdisplayedmonth - ticketmonth;
    if(monthdiff<0)
    {   
    	monthdiff*=-1;
    	for (int iterate=0; iterate<monthdiff; iterate++)
		{
	       WebElement button = this.driver.findElements(By.cssSelector(dateselector+" button")).get(1);
           button.click();
		}
    }
    else if(monthdiff>0)
    {
    	for (int iterate=0; iterate<monthdiff; iterate++)
		{
    		 WebElement button = this.driver.findElements(By.cssSelector(dateselector+" button")).get(0);
             button.click();
		}
    }
    else
    {
    	
    }
	
	//Choose Date     
    datewindow = this.driver.findElement(By.cssSelector(dateselector));
    for(WebElement button : datewindow.findElements(By.cssSelector("span")))
    {    	
	     if(button.getAttribute("innerHTML").contains(date))
	     {   
	    	 try 
	    	 {
	    		 new Actions(this.driver).doubleClick(button).build().perform();
	    		 this.wait.until(ExpectedConditions.invisibilityOf(datewindow));
	             break; 
	    	 }
	    	 catch(Exception e)
	    	 {
	    		 new Actions(this.driver).doubleClick(button).build().perform();
	    		 this.wait.until(ExpectedConditions.invisibilityOf(datewindow));
	             break; 
	    	 }    	 
	     }	
     }	
}

/**
 * This method is to set the number of passengers in the trip
 * @param adults
 * @param children
 * @param infants
 */
private void enterPassengerDetails(int adults, int children, int infants)
{  
	//open passenger popup
	this.ticketbutton.click();
	wait.until(ExpectedConditions.visibilityOf(this.passengerpopup));
	
   //Enter Adults
   int currentadults = Integer.parseInt(this.adultvalue.getAttribute("innerHTML"));	
   int adultdiff  = adults - currentadults;
   if(adultdiff<0)
	   adultdiff*=-1;
   if(adults<currentadults)
   {
	   for(int iterate=0; iterate<adultdiff; iterate++)
	   {
	      this.removeadult.click();
	   }	   
   }
   else if(adults>currentadults)
   {
       for(int iterate=0; iterate<adultdiff; iterate++)
	   {
	      this.addadult.click();
	   }
   } 
	
   //Enter Children
   int currentchildren = Integer.parseInt(this.chilndrenvalue.getAttribute("innerHTML"));
   int childdiff  = children - currentchildren;
   if(childdiff<0)
	   childdiff*=-1;
   if(children<currentchildren)
   {
	   for(int iterate=0; iterate<childdiff; iterate++)
	   {
	      this.removechildren.click();
	   }
   }
   else if(children>currentchildren)
   {
	   for(int iterate=0; iterate<childdiff; iterate++)
	   {
	      this.addchildren.click();
	   }
   }
	
   //Enter Infants	 
   int currinfants = Integer.parseInt(this.infantsvalue.getAttribute("innerHTML"));	
   int infantdiff  = infants - currinfants;
   if(infantdiff<0)
	   infantdiff*=-1;
   if(infants<currinfants)
   {
	   for(int iterate=0; iterate<infantdiff; iterate++)
	   {
	      this.removeinfants.click();
	   } 
   }
   else if(infants>currinfants)
   {
	   for(int iterate=0; iterate<infantdiff; iterate++)
	   {
	      this.addinfants.click();
	   } 
   }
	
}

/**
 * This method will choose the Ticket Type for Trip
 * @param ticketclass
 * @throws InterruptedException 
 */
private void chooseTicketClass(String ticketclass) throws InterruptedException
{
	this.buttonfaretype.click();
	this.wait.until(ExpectedConditions.visibilityOfAllElements(this.menuitems));
	
	for(WebElement item : this.menuitems)
	{
		if(item.getAttribute("innerHTML").equalsIgnoreCase(ticketclass.toLowerCase()))
		{
	       item.click();
	       break;
		}			
	}
	
	int timeout = 0;
	while(this.menuitems.size()==0 && ++timeout<15)
	{
		Thread.sleep(1000);		
	}
	new Actions(this.driver).click(this.okbutton.get(this.okbutton.size()-1)).build().perform();
	
}
	
}
