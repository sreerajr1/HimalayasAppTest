package app.himalayas.AutoScript;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import app.himalayas.POJO.JobSearchData;
import app.himalayas.TestComponents.BaseTest;

/**
 * @author sreeraj
 * 
 * Class contain Test case methods and Data Providers
 */
public class JobFilterTest extends BaseTest{
	

	@Test(dataProvider ="getData")
	public void Test1(JobSearchData jobSearchData)throws InterruptedException{
		
		JobSearchPOM jobsearch = homePage.goToJob();
		jobsearch.waitForElementToAppear(By.xpath("//h1[text()='Remote jobs']"));
		
		if(jobSearchData.getJobtitle()!=null) {
			jobsearch.inputJobTitle(jobSearchData.getJobtitle());
		}
		
		if(jobSearchData.getCountry()!=null) {
			jobsearch.inputCountry(jobSearchData.getCountry());
		}
		
		if(jobSearchData.getExperianceList()!=null) {
			jobsearch.inputExperienceLevel(jobSearchData.getExperianceList());
		}
		
		if(jobSearchData.getCurrency()!=null && jobSearchData.getSalaryRangeEnd()!=0 && jobSearchData.getSalaryRangeStart()!= 0){
			jobsearch.inputSalaryRange(jobSearchData.getCurrency(),jobSearchData.getSalaryRangeStart(), jobSearchData.getSalaryRangeEnd(), jobSearchData.isIncludeJobsWithoutSalary());
		}
        if(jobSearchData.getCompaniesList() !=null) {
        	jobsearch.inputCompany(jobSearchData.getCompaniesList());
		}
		
		if(jobSearchData.getJobTypeList()!=null) {
			jobsearch.inputJobType(jobSearchData.getJobTypeList());
		}
        if(jobSearchData.getEmployeeBenefitList()!=null) {
        	jobsearch.inputEmployeeBenefits(jobSearchData.getEmployeeBenefitList());
		}
        if(jobSearchData.getMarkets() != null) {
        	jobsearch.inputMarket(jobSearchData.getMarkets());
		}

        if(jobSearchData.getExpectedJobs() !=null) {
        	jobsearch.assertSearchResult(jobSearchData.getExpectedJobs());
		}
	}
	
	
	
	/**
	 * Method to read data from Excel usind Apache POI and sent to Test method through Data Provider
	 * @return Data as Objects Array of Type JobSearchData
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@DataProvider(name="getData")
	public Object[][] getData() throws IOException, InterruptedException {
		
		List <JobSearchData>jobsearchdataList= new ArrayList<JobSearchData>();
		
		FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir") +"\\TestData\\TestData.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		

		int rowCount = sheet.getPhysicalNumberOfRows();
		for(int i=1;i<rowCount;i++) {
			
			JobSearchData jobSearchData1 = new JobSearchData();
			 
			Row row = sheet.getRow(i);
			
			String listDelimiter = ", ";
			
			/**
			 * Read and Check whether the test case to be run or not
			 */
			
			if(row.getCell(0)!=null && row.getCell(0).getStringCellValue().equalsIgnoreCase("N")) {
				continue;
			}
			
			/**
			 * Read the Job Title from the Test Data Sheet
			 */
			if(row.getCell(2) != null) {
				jobSearchData1.setJobtitle(row.getCell(2).getStringCellValue());
			}
			/**
			 * Read the Country/Time Zone from the Test Data Sheet
			 */
			if(row.getCell(3) != null) {
				jobSearchData1.setCountry(row.getCell(3).getStringCellValue());
			}
			/**
			 * Read the Experience List from the Test Data Sheet
			 */
			if(row.getCell(4) != null) {
				jobSearchData1.setExperianceList(Arrays.asList(row.getCell(4).getStringCellValue().split(listDelimiter)));
			}
			/**
			 * Read the Currency, Salary Range etc.. from the Test Data Sheet
			 */	
			if(row.getCell(5) != null) {
				jobSearchData1.setCurrency(row.getCell(5).getStringCellValue());
			}
			
			if(row.getCell(6) != null) {	
			jobSearchData1.setSalaryRangeStart((int)row.getCell(6).getNumericCellValue());
			}
			
			if(row.getCell(7) != null) {
			jobSearchData1.setSalaryRangeEnd((int)row.getCell(7).getNumericCellValue());
			}
			
			if(row.getCell(8) != null) {
			jobSearchData1.setIncludeJobsWithoutSalary(row.getCell(8).getBooleanCellValue());
			}
			/**
			 * Read the Companies List from the Test Data Sheet
			 */
			if(row.getCell(9) != null) {
				jobSearchData1.setCompaniesList(Arrays.asList(row.getCell(9).getStringCellValue().split(listDelimiter)));
			}
			/**
			 * Read the Job Type List from the Test Data Sheet
			 */
			if(row.getCell(10) != null) {
				jobSearchData1.setJobTypeList(Arrays.asList(row.getCell(10).getStringCellValue().split(listDelimiter)));
			}
			/**
			 * Read the Employee Benefits List from the Test Data Sheet
			 */
			if(row.getCell(11) != null) {
				jobSearchData1.setEmployeeBenefitList(Arrays.asList(row.getCell(11).getStringCellValue().split(listDelimiter)));
			}
			/**
			 * Read the Market List from the Test Data Sheet
			 */
			if(row.getCell(12) != null) {
				jobSearchData1.setMarkets(Arrays.asList(row.getCell(12).getStringCellValue().split(listDelimiter)));
			}
			/**
			 * Read the Expected Search Output from the Test Data Sheet
			 */
			if(row.getCell(13) != null) {
				jobSearchData1.setExpectedJobs(Arrays.asList(row.getCell(13).getStringCellValue().split(listDelimiter)));;
			}
			
			jobsearchdataList.add(jobSearchData1);
			
			
		}
	
		workbook.close();
			
		Object [][] objArray = new Object[jobsearchdataList.size()][];

		for(int i=0;i< jobsearchdataList.size();i++){
		    objArray[i] = new Object[1];
		    objArray[i][0] = jobsearchdataList.get(i);
		 } 
		
		 return objArray;
	}
	
	
}
