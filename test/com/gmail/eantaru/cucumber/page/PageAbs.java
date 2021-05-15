package com.gmail.eantaru.cucumber.page;

import org.openqa.selenium.WebDriver;

public abstract class PageAbs {
	
	private static final String page = "https://www.tokopedia.com/p/handphone-tablet/handphone";
	
	private final WebDriver driver;
	
	public PageAbs(BrowserAdapter adapter) {
		
		driver = adapter.getBrowser();
		driver.manage().window().maximize();
	}
	
	protected WebDriver getDriver() {
		return driver;
	}

}
