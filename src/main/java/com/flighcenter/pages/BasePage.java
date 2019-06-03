package com.flighcenter.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * Base Page class -  Super class of all Page classes.
 * @author lakshman.shiva
 * @since  03-June-2019
 */
public class BasePage {

private static final int TIMEOUT = 5;
protected WebDriver driver;

public BasePage(WebDriver driver){	
	this.driver = driver;	
	PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
}
	
}
