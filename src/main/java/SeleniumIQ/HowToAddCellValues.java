package SeleniumIQ;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.testproject.sdk.DriverBuilder;
import io.testproject.sdk.drivers.web.ChromeDriver;

public class HowToAddCellValues {

	static ChromeDriver driver;

	@Test
	public static void verifySumPts() throws IOException {

		WebDriverManager.chromedriver().setup();

		driver = new DriverBuilder<ChromeDriver>(new ChromeOptions())
				.withRemoteAddress(new URL("http://localhost:8585"))
				.withToken("b9nE-ctdn354FBqALlOTPYncmjfGMLhIYHfW7gGemN81").build(ChromeDriver.class);

		driver.manage().window().maximize(); // Maximize the window
		driver.manage().deleteAllCookies(); // Delete all cookies
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.iplt20.com/points-table/men/2020");
		addPts();
		driver.close();

	}

	// b9nE-ctdn354FBqALlOTPYncmjfGMLhIYHfW7gGemN81

	public static void addPts() {

		// Identify the web table
		WebElement webTable = driver.findElement(By.xpath("//tbody"));

		// Get the total no of rows present in web table
		List<WebElement> rows = webTable.findElements(By.tagName("tr"));
		int rowsCount = rows.size();
		System.out.println("Total no of rows in web table is : " + rowsCount);

		int pts = 0;
		int sum = 0;
		for (int i = 2; i <= rowsCount; i++) {

			WebElement ptsValue = driver.findElement(By.xpath("//table[1]/tbody[1]/tr[" + i + "]/td[11]"));
			pts = Integer.parseInt(ptsValue.getText());
			sum = sum + pts;
		}

		System.out.println("Sum of Point table is :- " + sum);
	}
}
