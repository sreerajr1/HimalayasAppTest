package app.himalayas.TestComponents;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import app.himalayas.AutoScript.HomePagePOM;


/**
 * @author sreeraj 
 * Class used to method common for every test
 */
public class BaseTest {
	
	WebDriver driver;
	
	protected HomePagePOM homePage;

	
	/**
	 * Initialize Driver on start of each test
	 */
	public void initializeDriver() {
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}
	
	/**
	 * Method to run before the start of each test
	 */
	@BeforeMethod
	public void launchApplication() {
		initializeDriver();
		homePage = new HomePagePOM(driver);
		homePage.getHome();
		
	}
	
	/**
	 * Method to run after each test
	 */
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
}
