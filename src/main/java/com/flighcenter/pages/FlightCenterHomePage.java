package com.flighcenter.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * This is home page of flight center page when the URl is launched.
 * @author lakshman.shiva
 * @since 03-June-2019
 */
public class FlightCenterHomePage extends BasePage {

private static final int TIMEOUT = 5;

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
	
public FlightCenterHomePage(WebDriver driver)
{
	super(driver);	
	PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
}

/**
 * Flight Search based on different parameters * 
 */
public void searchFlight(boolean isoneway, String from, String to, String ticketclass, 
		String departdate, String retunrdate)
{
	if(isoneway)
		this.onewaytrip.click();	
	this.enterFlyFromTo(from, to);
	
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
   this.flyingfrom.sendKeys(flyto);
   this.alldestinations.get(0).click();
}
	
}
