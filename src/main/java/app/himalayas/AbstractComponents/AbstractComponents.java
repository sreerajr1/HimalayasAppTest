package app.himalayas.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import app.himalayas.AutoScript.JobSearchPOM;

/**
 * @author sreeraj
 * 
 * Class used to hold elements and method common to all pages 
 */
public class AbstractComponents {
	
	WebDriver driver;
	
	@FindBy(xpath="//button[text()='Jobs']")
	WebElement jobNavigation;
	
	/**
	 * @param driver
	 * will initiate the driver
	 */
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param findBy
	 * Explicit wait till the Element is Appeared 
	 */
	public void waitForElementToAppear(By findBy){
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
			
	}
	
	/**
	 * Navigat to Home Screen
	 */
	public void getHome() {
		driver.get("https://himalayas.app");
	}

	
	/**
	 * Navigate to JobSEarch Screen
	 * @return jobsearch Page POM object
	 */
	public JobSearchPOM goToJob() {
		jobNavigation.click();
		JobSearchPOM jobsearch = new JobSearchPOM(driver);
		jobsearch.waitForElementToAppear(By.xpath("//h1[text()='Remote jobs']"));
		jobNavigation.click();
		return jobsearch;
	}

}
