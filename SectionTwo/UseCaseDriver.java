
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class UseCaseDriver {

	private WebDriver driver;
	private TestPage tp;
	private static final String testURL = "http://t.hostelworld.com";
	private static final String searchTerm = "Dublin, Ireland";

	public UseCaseDriver(String sortFilter) {
	
		driver = new FirefoxDriver();
		tp = new TestPage(driver);
		
		// below steps 'mock' a testng sequence of test steps
		
		// 1) Firstly go to the testing site
		tp.goToURL(testURL);
		
		// 2) now enter search term and submit
		tp.submitMainSearch(searchTerm);
		
		// 3) Now sort based on the use case
		tp.sortBy(sortFilter);
		
		System.out.println("Going to check if URL contains sort filter...");
		
		// 4) Now verify we're on a sorted by <name|price> search results page
		tp.verifySort(sortFilter);
		
		// 5) Close the webdriver
		System.out.println("Test passed, closing driver, exiting...");
		tp.closeDriver();
		System.exit(0);
	}

	public static void main(String[] args) {
		
		System.out.println("printing arg count: "+args.length);
		
		if(args.length < 1) {
			System.err.println("Error, please pass 'Name' or 'Price' as a param when executing... exiting...");
			System.exit(1);
		} else {
			
			// we only care about the first param, expecting name or price
			String sortFilter = args[0];
			
			if((sortFilter.toLowerCase().equals("name")) || (sortFilter.toLowerCase().equals("price"))){
				
				System.out.println("Preparing to search with filter: "+sortFilter+" criteria");
			
				UseCaseDriver ucd = new UseCaseDriver(sortFilter);
			} else {		
				System.err.println("Error, please pass 'Name' or 'Price' as a param when executing... exiting...");
				System.exit(1);		
			}
		}
		
	}
}