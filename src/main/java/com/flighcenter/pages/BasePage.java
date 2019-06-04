package com.flighcenter.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base Page class -  Super class of all Page classes.
 * @author lakshman.shiva
 * @param <T>
 * @since  03-June-2019
 */
public class BasePage <T extends LoadableComponent<T>> extends LoadableComponent<T>{

private static final int TIMEOUT = 5;
private static final int POLLING = 100;
protected WebDriver driver;
protected WebDriverWait wait;

public BasePage(WebDriver driver){	
	this.driver = driver;	
	wait = new WebDriverWait(driver, TIMEOUT, POLLING);		
}

@Override
protected void isLoaded() throws Error {
	// TODO Auto-generated method stub
	
}

@Override
protected void load() {
	// TODO Auto-generated method stub
	
}


	
}
