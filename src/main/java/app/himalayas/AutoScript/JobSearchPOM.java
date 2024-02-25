package app.himalayas.AutoScript;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import app.himalayas.AbstractComponents.AbstractComponents;

/**
 * @author sreeraj
 * POM class for Managing Elements in the Job Search page
 */
public class JobSearchPOM extends AbstractComponents{
	
	WebDriver driver;
	
	
	
	/**
	 * Elements in the Job Search Page
	 * 
	 * Initiated during instance creation
	 * 
	 */
	@FindBy(xpath="//input[@placeholder='Job title or skill']")
	WebElement jobTitleSearch;
	
	@FindBy(xpath="//input[@placeholder='Country or timezone']")
	WebElement countrySearch;
	
	@FindBy(xpath="//button[text()='Experience level']")
	WebElement experienceLevelFilter;
	
	@FindBy(xpath="//button[text()='Salary range']")
	WebElement salaryRangeFilter;
	
	@FindBy(xpath="//div/button[text()='Companies']")
	WebElement companiesFilter;
	
	@FindBy(xpath="//button[text()='Job type']")
	WebElement jobTypeFilter;
	
	@FindBy(xpath="//button[text()='Employee benefits']")
	WebElement employeeBenefitsFilter;
	
	@FindBy(xpath="//button[text()='Markets']")
	WebElement marketsFilter;
	
	
	
	/**
	 * 
	 * Initialize the driver
	 * @param driver
	 */
	public JobSearchPOM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	/**
	 * Method to select the options in the Experience level filter button
	 * @param experienceLevels - List of Options to select
	 */
	public void inputExperienceLevel(List<String> experienceLevels) {
		
		experienceLevelFilter.click();
		waitForElementToAppear(By.xpath("//fieldset"));
				
		WebElement experianceList = driver.findElement(By.xpath("//fieldset"));
		
		experianceList.findElement(By.xpath("//button[text()='Deselect all']")).click();
		waitForElementToAppear(By.xpath("//button[text()='Select all']"));
		
		for(String experienceLevel : experienceLevels) {
			experianceList.findElement(By.xpath("//button[@id='"+experienceLevel+"']")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		driver.findElement(By.xpath("//button[contains(text(),'experience level')]")).click();
		
	}
	
	
	/**
	 * Method to select the options in the Job Type filter button
	 * @param jobTypes - List of Options to select
	 */
	public void inputJobType(List<String> jobTypes) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		
		jobTypeFilter.click();
		waitForElementToAppear(By.xpath("//fieldset"));
				
		WebElement jobTypeList = driver.findElement(By.xpath("//fieldset"));
		
		jobTypeList.findElement(By.xpath("//button[text()='Deselect all']")).click();
		waitForElementToAppear(By.xpath("//button[text()='Select all']"));
		
		for(String jobType : jobTypes) {
			jobTypeList.findElement(By.xpath("//button[@id='"+jobType+"']")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.xpath("//button[contains(text(),'job type')]")).click();
		
		js.executeScript("window.scrollBy(0,-250)", "");
	}

	
    /**
     * Method to select the options in the Salary Range filter button
     * @param currency 
     * @param start
     * @param end
     * @param includeJobWithoutSalary
     * @throws InterruptedException
     */
    public void inputSalaryRange(String currency, int start, int end, Boolean includeJobWithoutSalary) throws InterruptedException {

    	salaryRangeFilter.click();
    	
    	WebElement element = driver.findElement(By.xpath("//span[contains(text(),'United States Dollars')]"));
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.elementToBeClickable(element)); 

    	Actions actions = new Actions(driver); 
    	actions.moveToElement(element).click().build().perform();
    	
    	
    	List<WebElement> currencylist = driver.findElements(By.xpath("//div/div/div/div[contains(@class, 'relative my-0.5 flex w-full cursor-pointer select-none items-center rounded')]"));
    	
    	for(WebElement ccy :currencylist) {
			String str1= ccy.getText();
			if(str1.contains(currency)) {
			ccy.click();
			break;
			}
    	}
    	
    	Thread.sleep(3000);
    	WebElement slider = driver.findElement(By.xpath("//span[@class='relative h-1.5 w-full grow cursor-pointer overflow-hidden rounded-full bg-gray-200']"));
    	
    	
    	int width = slider.getSize().getWidth();
    	String rangeEnd = driver.findElement(By.xpath("//div[@class='flex justify-between']/p[2]")).getText();
    	rangeEnd = rangeEnd.substring(1,rangeEnd.length()-1);
    	rangeEnd = rangeEnd.replace(",","");
    	int maxSalary = Integer.parseInt(rangeEnd);

    	
    	WebElement startPoint = driver.findElement(By.xpath("//span[@class='relative flex w-full touch-none select-none items-center']/span[2]"));
    	WebElement endPoint = driver.findElement(By.xpath("//span[@class='relative flex w-full touch-none select-none items-center']/span[3]"));
    	int startPosition = start*width/maxSalary;

    	int endPosition = end*width/maxSalary;
    	
    	actions.clickAndHold(startPoint).moveToElement(slider,startPosition-(width/2),0).release().build().perform();
    	actions.clickAndHold(endPoint).moveToElement(slider,endPosition-(width/2),0).release().build().perform();
    	
    	waitForElementToAppear(By.xpath("//button[contains(text(),' - ' )]"));
    	if(!includeJobWithoutSalary) {
    		driver.findElement(By.xpath("//label[text()='Include jobs without salary']/button")).click();
    	}
    	
    	driver.findElement(By.xpath("//button[contains(text(),' - ' )]")).click();

    }
    
    
    /**
     * Method to select the options in the Companies filter button
     * @param companyNames - List of Options to select
     */
    public void inputCompany(List<String> companyNames) {
    	
    	companiesFilter.click();
    	
    	waitForElementToAppear(By.xpath("//p[text()='Companies']"));
    	
    	WebElement nameinput =driver.findElement(By.xpath("//input[@placeholder='Company name']"));
    	
    	for(String companyName : companyNames) {
    		nameinput.sendKeys(companyName);
    		waitForElementToAppear(By.xpath("//span[text()='"+companyName+"']"));
    		
    		driver.findElement(By.xpath("//span[text()='"+companyName+"']")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nameinput.clear();
    		
    	}
    	driver.findElement(By.xpath("//button[contains(text(),'compan')]")).click();
    	
    	
    }
    
    
    
    /**
     * Method to select the options in the Employee Benefits filter button
     * @param employeeBenefits - List of Options to select
     */
    public void inputEmployeeBenefits(List<String> employeeBenefits) {
    	
    	employeeBenefitsFilter.click();
    	
    	waitForElementToAppear(By.xpath("//p[text()='Employee benefits']"));
    	
    	WebElement nameinput =driver.findElement(By.xpath("//input[@placeholder='Benefit or perk']"));
    	
    	for(String employeeBenefit : employeeBenefits) {
    		nameinput.sendKeys(employeeBenefit);
    		waitForElementToAppear(By.xpath("//span[text()='"+employeeBenefit+"']"));
    		
    		driver.findElement(By.xpath("//span[text()='"+employeeBenefit+"']")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nameinput.clear();
    		
    	}
    	driver.findElement(By.xpath("//button[contains(text(),'benefit')]")).click();
    	
    }
    
    
    /**
     * Method to select the options in the Market filter button
     * @param Markets - List of Options to select
     */
    public void inputMarket(List<String> Markets) {
    	
    	marketsFilter.click();
    	
    	waitForElementToAppear(By.xpath("//p[text()='Markets']"));
    	
    	WebElement nameinput =driver.findElement(By.xpath("//input[@placeholder='Search']"));
    	
    	for(String market : Markets) {
    		nameinput.sendKeys(market);
    		waitForElementToAppear(By.xpath("//span[text()='"+market+"']"));
    		
    		driver.findElement(By.xpath("//span[text()='"+market+"']")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nameinput.clear();
    		
    	}
    	driver.findElement(By.xpath("//button[contains(text(),'market')]")).click();
    	
    }
    
    
    /**
     * Method to input the Job Title search field
     * @param jobTitle
     */
    public void inputJobTitle(String jobTitle) {
    	
    	
    	jobTitleSearch.sendKeys(jobTitle);
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	jobTitleSearch.sendKeys(Keys.ENTER);
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	waitForElementToAppear(By.xpath("//h1/span[text()='"+jobTitle+"']"));
    		
    	
    }
    
    /**
     * Method to input the Country or Time zone search field
     * @param country
     */
    public void inputCountry(String country) {
    	
    	countrySearch.sendKeys(country);
    	
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	countrySearch.sendKeys(Keys.ENTER);
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  waitForElementToAppear(By.xpath("//h1/span[text()='"+country+"']"));
    }
    
    /**
     * Method to verify the actual Jobs filtered and expected Jobs to be filtered
     * @param expectedJobs
     */
    public void assertSearchResult(List<String> expectedJobs) {
    	
    	List <WebElement>actualjobs = driver.findElements(By.xpath("//article/div/div/a"));
    	
    	for (int i=0 ; i< expectedJobs.size();i++) {
    		
    		Assert.assertEquals(actualjobs.get(i).getText(),expectedJobs.get(i));
    	}
    	
    	
    }

}
