package app.himalayas.POJO;

import java.util.List;


/**
 * @author sreeraj
 * POJO class for the passing of Data to TestNg through Data Provider
 *
 */
public class JobSearchData {

	private String jobtitle;
	
	private String country;
	
	private String currency;
	
	private int salaryRangeStart;
	
	private int salaryRangeEnd;
	
	private boolean isIncludeJobsWithoutSalary;
	
	private List<String> experianceList;
	
	private List<String> jobTypeList;
	
	private List<String> CompaniesList;
	
	private List<String> EmployeeBenefitList;
	
	private List<String> markets;
	
	private List<String> expectedJobs;

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getSalaryRangeStart() {
		return salaryRangeStart;
	}

	public void setSalaryRangeStart(int salaryRangeStart) {
		this.salaryRangeStart = salaryRangeStart;
	}

	public int getSalaryRangeEnd() {
		return salaryRangeEnd;
	}

	public void setSalaryRangeEnd(int salaryRangeEnd) {
		this.salaryRangeEnd = salaryRangeEnd;
	}

	public boolean isIncludeJobsWithoutSalary() {
		return isIncludeJobsWithoutSalary;
	}

	public void setIncludeJobsWithoutSalary(boolean isIncludeJobsWithoutSalary) {
		this.isIncludeJobsWithoutSalary = isIncludeJobsWithoutSalary;
	}

	public List<String> getExperianceList() {
		return experianceList;
	}

	public void setExperianceList(List<String> experianceList) {
		this.experianceList = experianceList;
	}

	public List<String> getJobTypeList() {
		return jobTypeList;
	}

	public void setJobTypeList(List<String> jobTypeList) {
		this.jobTypeList = jobTypeList;
	}

	public List<String> getCompaniesList() {
		return CompaniesList;
	}

	public void setCompaniesList(List<String> companiesList) {
		CompaniesList = companiesList;
	}

	public List<String> getEmployeeBenefitList() {
		return EmployeeBenefitList;
	}

	public void setEmployeeBenefitList(List<String> employeeBenefitList) {
		EmployeeBenefitList = employeeBenefitList;
	}

	public List<String> getMarkets() {
		return markets;
	}

	public void setMarkets(List<String> markets) {
		this.markets = markets;
	}

	public List<String> getExpectedJobs() {
		return expectedJobs;
	}

	public void setExpectedJobs(List<String> expectedJobs) {
		this.expectedJobs = expectedJobs;
	}
	
	
	
} 
