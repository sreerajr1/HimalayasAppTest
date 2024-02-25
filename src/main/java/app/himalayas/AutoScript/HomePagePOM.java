package app.himalayas.AutoScript;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import app.himalayas.AbstractComponents.AbstractComponents;

/**
 * @author sreeraj
 * POM class for Managing Elements in the home page
 */
public class HomePagePOM extends AbstractComponents {
	
		
	WebDriver driver;
	
	
	/**
	 * Initialize the driver when objects crated
	 * @param driver
	 */
	public HomePagePOM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

}
