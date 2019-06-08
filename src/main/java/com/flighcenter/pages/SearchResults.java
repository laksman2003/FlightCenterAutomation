package com.flighcenter.pages;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchResults extends BasePage<SearchResults> {

	private static final int TIMEOUT = 5;
	private static final String farerates = "div[class*='fare-details']";
	private static final String pricedollar = "div[class*='fare-details'] div.price-text>span.price-int";
	private static final String pricecents = "div[class*='fare-details'] div.price-text>span.decimal";
	
	@FindBy(css=farerates)
	List<WebElement> allsearchitems;	
	
	@FindBy(css=pricedollar)
	List<WebElement> costdollar;
	
	@FindBy(css=pricecents)
	List<WebElement> costcent;
		
	protected SearchResults(WebDriver driver) {
		super(driver);	
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
	}
    
	@Override
	protected void isLoaded() throws Error {        
	
		try {
			
			//Wait for search tab and switch to search tab 
			ExpectedCondition<Boolean> newtab = new
		            ExpectedCondition<Boolean>() {
		                public Boolean apply(WebDriver driver) {
		                	Set<String> winhndle = driver.getWindowHandles();
		                    return winhndle.size()>1?true:false;
		                }
		            };
		    wait.until(newtab);	
		    this.driver.switchTo().window(((String)driver.getWindowHandles().toArray()[1]));
		    
			//wait for page load
		    ExpectedCondition<Boolean> pageLoadCondition = new
		            ExpectedCondition<Boolean>() {
		                public Boolean apply(WebDriver driver) {                	
		                    return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
		                }
		            };
		    wait.until(pageLoadCondition); 
		    
		    //wait for search result page
		    this.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(farerates)));	
		}
		catch(Exception e)
		{
			throw new Error("Issue in loading Search Results Page");
		}
	  	
	}

	@Override
	protected void load() {	}
	
	/**
	 * This method return price of each flight options listed in Search page
	 * This method needs to be invokes after searchFlight() in Flight center home page 
	 * @return List<Double>
	 */
	public List<Double> getFlightCost()
	{
		List<Double> cost = new ArrayList<Double>();
		for(int iterate=0; iterate<this.allsearchitems.size(); iterate++)
		{   
					cost.add((Double.parseDouble(this.costdollar.get(iterate).getAttribute("innerHTML").replace(",", "")))
					+(Double.parseDouble(this.costcent.get(iterate).getAttribute("innerHTML"))));
	    }		
		
		return cost;		
	}
			
	
}
