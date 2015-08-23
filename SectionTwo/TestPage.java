
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



/*
* Page object for the t.hostelworld.com testing site
* i pass in the url but other ids are constants that
* that relate to this 'page'
*/


public class TestPage {

	private WebDriver driver;
	private static final String searchFieldID = "home-search-keywords";
	private static final String sortCssPath = "html.js.flexbox.flexboxlegacy.canvas.canvastext.webgl.no-touch.geolocation.postmessage.no-websqldatabase.indexeddb.hashchange.history.draganddrop.websockets.rgba.hsla.multiplebgs.backgroundsize.borderimage.borderradius.boxshadow.textshadow.opacity.cssanimations.csscolumns.cssgradients.no-cssreflections.csstransforms.csstransforms3d.csstransitions.fontface.generatedcontent.video.audio.localstorage.sessionstorage.webworkers.applicationcache.svg.inlinesvg.smil.svgclippaths body#pagebody.hwta-page-city.cardView div.off-canvas-wrap div.inner-wrap div.page-contents div.contentbackground div.fixedfilters div.row div.small-12.columns div.clearfix.fabtopfilters div.clearfix.hide-for-small dl.sub-nav.leftfilters.left dd.topfilter.rounded.sort-button span";
	
	public TestPage(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void goToURL(String testURL) {
		
		driver.get(testURL);
		
		// just explicitly wait until satisfied we're on the frontpage
		new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(By.id(searchFieldID)));
	}
	
	public void submitMainSearch(String searchTerm) {
		// locate the main search textfield
		WebElement mainSearchTextfield = driver.findElement(By.id(searchFieldID));
		
		mainSearchTextfield.sendKeys(searchTerm);
		
		// i caught selenium being too fast and offering me a page not seen when i manually performed the step
		// so i made it more user-like and wait on the js to offer up a suggestion - did the trick
		new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(By.className("suggestion ")));
		
		// running out of time here, so just 'hit enter' on the search field
		// not going to find the search button or predictive best effort offered search term
		mainSearchTextfield.submit();

		// don't just presume page loads in 1ms - wait until allowable amount of time before moving on
		new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(sortCssPath)));
	}
	
	public void sortBy(String sortOpt) {
		
		String sortByOption="";
		
		if(sortOpt.equals("name")){
			sortByOption = "sortByName";
		} else if(sortOpt.equals("price")) {
			sortByOption = "sortByPrice";
		} else {
			System.err.println("Opps somehow this isn't name or price, exiting...");
		}
		
		// click the sort element
		getSortElement().click();
		
		// wait until we see the items listed from clicking that
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.id(sortByOption)));
		
		// now click either name or price
		driver.findElement(By.id(sortByOption)).click();
	}
	
	private WebElement getSortElement() {
		return driver.findElement(By.cssSelector(sortCssPath));
	}
	
	public void verifySort(String sortFilter) {
		// no time to check complicated traversal of the listed items 
		// i'm only going to check the URL ends with name or price, sorry
		// if selenium/java is too fast or testing site is too slow when 
		// you run this - the url bar mightn't contain sort=XYZ
		// proper thing to do would be to write own expectedcondition on contents of currentUrl...
		if(driver.getCurrentUrl().endsWith("sort="+sortFilter)){
			System.out.println("Confirmed we've sorted by "+sortFilter);
		} else {
			System.err.println("The page's title doesn't end with "+sortFilter+", exiting...");
			closeDriver();
			System.exit(1);
		}
	}
	
	public void closeDriver() {
		driver.close();
	}
}