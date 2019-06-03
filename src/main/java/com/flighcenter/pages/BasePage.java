package com.flighcenter.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base Page class -  Super class of all Page classes.
 * @author lakshman.shiva
 * @since  03-June-2019
 */
public class BasePage {

private static final int TIMEOUT = 5;
private static final int POLLING = 100;
protected WebDriver driver;
protected WebDriverWait wait;

public BasePage(WebDriver driver){	
	this.driver = driver;	
	wait = new WebDriverWait(driver, TIMEOUT, POLLING);	
	PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
}
	
}
