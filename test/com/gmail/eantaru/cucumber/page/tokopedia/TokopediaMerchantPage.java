package com.gmail.eantaru.cucumber.page.tokopedia;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.gmail.eantaru.cucumber.page.ChromeBrowser;
import com.gmail.eantaru.cucumber.page.PageAbs;

public class TokopediaMerchantPage extends PageAbs {
	
	private static final String body = "//body[@class='pdp-container']/div[@id='zeus-root']/div[@class='css-3lpl5n']";
	private static final String rating = "//div[@class='items']/div[2]/span[1]/span[@class='main']";
	private static final String desc = "//div[@class='css-1k1relq']/span[@class='css-11oczh8 e1iszlzh0']/span[@class='css-17zm3l e1iszlzh1']/div";
	private static final String merchant = "//div[@class='css-1ptigug']/div/a[@class='css-1n8curp']";
	private static final String image = "//div[@class='css-1aplawl active']/div[@class='css-19i5z4j']/img[@class='success fade']/@src";
	
	// direct temp
	private String textRating;
	private String textDesc;
	private String textMerchant;
	private String textImage;
	
	public TokopediaMerchantPage() {
		super(new ChromeBrowser());
	}
	
	public void navigate(String page) {
		if(!page.contains("https://ta.")) {
			getDriver().get(page);
			
			waitAndScrape();
		}
		
	}
	
	// blocking and safe
	private void waitAndScrape() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())							
				.withTimeout(Duration.ofSeconds(30)) 			
				.pollingEvery(Duration.ofSeconds(5))			
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver ) {
				return driver.findElement(By.xpath(body));
			}
		});

		try {
			textRating = element.findElement(By.xpath(rating)).getText();
			textMerchant = element.findElement(By.xpath(merchant)).getText();
		
			textDesc = element.findElement(By.xpath(desc)).getText();
			textImage = element.findElement(By.xpath(image)).getText();
		} catch (InvalidSelectorException e) {
		}
	}
	
	public String getRating() {
		return textRating;
	}
	
	public String getMerchant() {
		return textMerchant;
	}

	public String getDesc() {
		return textDesc;
	}
	
	public String getImage() {
		return textImage;
	}

}
