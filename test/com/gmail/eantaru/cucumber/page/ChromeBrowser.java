package com.gmail.eantaru.cucumber.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser implements BrowserAdapter {
	
	private static final String path = "/usr/local/bin/chromedriver";

	@Override
	public WebDriver getBrowser() {

	    System.setProperty("webdriver.chrome.driver", path);
		
		return new ChromeDriver();
	}

}
