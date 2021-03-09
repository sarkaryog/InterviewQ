package SeleniumIQ;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HowToHandleCalendar {
	
	/**
	 * Logic for handle calendar Inspect the month year header Get the header text
	 * and split it and store in a String array Create a loop and clicking on it
	 * until desire month year not selected Select a date when desired month year
	 * appear Put if condition for February month
	 */

	static WebDriver driver;

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(); // Open the browser
		driver.manage().window().maximize(); // Maximize the window
		driver.manage().deleteAllCookies(); // Delete all cookies
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-handle-calendar-in-selenium.html");
		driver.findElement(By.id("datepicker")).click();

		// new WebDriverWait(driver, Duration.ofSeconds(20))
		// .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ui-datepicker-calendar")));

		selectDate("10", "March", "2021"); // Select the date from calendar
		
		driver.close();
	}

	// Split the month year value which is extracted from calendar header
	public static String[] getMonthYear(String monthYearVal) {
		return monthYearVal.split(" ");
	}

	// Method for clicking expected date from calendar
	public static void selectDate(String expDay, String exMonth, String exYear) {

		// Get the header text from calendar
		String monthYearVal = driver.findElement(By.className("ui-datepicker-title")).getText();

		// Create a condition for February month
		if (exMonth.equals("February") && Integer.parseInt(expDay) > 29) {

			System.out.println("Wrong Date:- " + exMonth + " doesn't have: " + expDay + " Days");
			return;

		}

		// If days count is greater than 31 days
		if (Integer.parseInt(expDay) > 31) {

			System.out.println("Wrong Date:- " + exMonth + " doesn't have: " + expDay + " Days");
			return;

		}
			
		System.out.println(monthYearVal);

		// Clicking on next until month and year doesn't get match as per user input
		while (!(getMonthYear(monthYearVal)[0].equals(exMonth) && getMonthYear(monthYearVal)[1].equals(exYear))) {

			driver.findElement(By.xpath("//a[@title='Next']")).click();
			monthYearVal = driver.findElement(By.className("ui-datepicker-title")).getText();
		}

		// Clicking on the date when year and month is matched with user requirement
		driver.findElement(By.xpath("//a[text()='" + expDay + "']")).click();

	}

}
