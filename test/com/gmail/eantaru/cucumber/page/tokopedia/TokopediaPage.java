package com.gmail.eantaru.cucumber.page.tokopedia;

import java.time.Duration;
import java.util.Iterator;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.gmail.eantaru.cucumber.page.ChromeBrowser;
import com.gmail.eantaru.cucumber.page.PageAbs;
import com.gmail.eantaru.cucumber.storage.Storage;
import com.gmail.eantaru.cucumber.storage.model.TokopediaModel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TokopediaPage extends PageAbs {
	
	private static final String cards = "//div[@class='css-13wayc1']/div[@class='css-1dq1dix e1nlzfl1']/div[@class='css-13l3l78 e1nlzfl10']/div[@class='css-bk6tzz e1nlzfl3']";
	private static final String card = "//div[@class='css-bk6tzz e1nlzfl3']";
	private static final String cardTitle = "a[@class='css-89jnbj']/div[@class='css-16vw0vn']/div[@class='css-11s9vse']/span[@class='css-1bjwylw']";
	private static final String cardPrice = "a[@class='css-89jnbj']/div[@class='css-16vw0vn']/div[@class='css-11s9vse']/div[1]/div[@class='css-4u82jy']/span[@class='css-o5uqvq']";
    private static final String cardLink = "a";
    
	private static final String page = "https://www.tokopedia.com/p/handphone-tablet/handphone";
	
	private int currentPage = 1;
	
	private TokopediaMerchantPage merchantPage;
	
	public TokopediaPage() {
		super(new ChromeBrowser());
	}

	@Given("^A user browse tokopedia mobile phone page$")
	public void browseTokopedia() throws Throwable {
	    getDriver().get(page);
	}

	@Then("^Wait until page fully open and Scroll down and list all phones$")
	public void waitPageAndScroll() throws Throwable {
		scrapePage();
	}
	
	private void scrapePage() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		int dataSize = getDriver().findElements(By.xpath(cards)).size();
		
		do{
		    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		    
			Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())							
					.withTimeout(Duration.ofSeconds(30)) 			
					.pollingEvery(Duration.ofSeconds(5))			
					.ignoring(NoSuchElementException.class);
		    
			WebElement listofElement = wait.until(new Function<WebDriver, WebElement>(){
				public WebElement apply(WebDriver driver ) {
					return driver.findElement(By.xpath(cards));
				}
			});
			
			Iterator<WebElement> it = listofElement.findElements(By.xpath(card))
					.iterator();
			
			
			while(it.hasNext()) {
				
				WebElement element = it.next();

		        WebElement link  = element.findElement(By.xpath(cardLink));
		        
				TokopediaModel model = new TokopediaModel.TokopediaModelBuilder()
						.product(element.findElement(By.xpath(cardTitle)).getText())
						.price(element.findElement(By.xpath(cardPrice)).getText())
						.link(link.getAttribute("href"))
						.build();
		       
		        openMerchantPage(page, model);
				
				Storage.instanceStorage().store(model);
				
			}
			
			
			if(getDriver().findElements(By.xpath(cards)).size() == dataSize ||
					Storage.instanceStorage().isReachLimit()) {
				if(!Storage.instanceStorage().isReachLimit()) {
					getDriver().get(page + "?page=" + (++ currentPage));
				}
				
				break;
			}
			
			dataSize = getDriver().findElements(By.xpath(cards)).size();
		}
		while(!Storage.instanceStorage().isReachLimit());
		
		Storage.instanceStorage().print();
	}
	
	private TokopediaModel openMerchantPage(String page, TokopediaModel model) {
		if(merchantPage == null) {
        	merchantPage = new TokopediaMerchantPage();
        }
		
		merchantPage.navigate(page);

		model.setRating(merchantPage.getRating());
		model.setMerchant(merchantPage.getMerchant());
		model.setDesc(merchantPage.getDesc());
		model.setImage(merchantPage.getImage());
		
		return null;
	}
	
	@Then("^Close the browser$")
	public void closeBrowser() {
		getDriver().quit();
	}
	
	@Then("^Store from volatile storage to csv$")
	public void storeToCSV() throws Throwable {
		Storage.instanceStorage().storeToCSV();
	}

}
