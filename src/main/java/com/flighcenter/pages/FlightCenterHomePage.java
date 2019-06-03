package com.flighcenter.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is home page of flight center page when the URl is launched.
 * @author lakshman.shiva
 * @since 03-June-2019
 */
public class FlightCenterHomePage extends BasePage {

private static final int TIMEOUT = 5;
private static final int POLLING = 100;
private WebDriverWait wait;

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
	
public FlightCenterHomePage(WebDriver driver)
{
	super(driver);
	wait = new WebDriverWait(driver, TIMEOUT, POLLING);
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
	
	this.flyingfrom.click();
	this.flyingfrom.sendKeys("");
}
	
}
