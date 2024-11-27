package FitpeoAssesment_Test;

import java.awt.AWTException;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assignmene_Test {
	@Test

	public void taskAssignment() throws AWTException, InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
       //Navigate to Homepage
		driver.get("https://fitpeo.com/home");
		driver.manage().window().maximize();
		
		//Navigate to Revenue Calculator page
		driver.findElement(By.xpath("//div[text()='Revenue Calculator']")).click();
        //setting Total Individual Patient/Month value using slider and validating
		Actions act = new Actions(driver);
        WebElement silderElement = driver.findElement(By.xpath("//span[@class='MuiSlider-rail css-3ndvyc']"));
		act.scrollByAmount(0, 200);
		act.moveToElement(silderElement).click().build().perform();
		WebElement textValue = driver.findElement(By.id(":r0:"));

		for (;;) {
			String val = textValue.getAttribute("value");
			int actNum = Integer.parseInt(val);
			if (actNum == 820) {
			  break;
			} else {
				act.keyDown(Keys.DOWN).perform();

			}
		}
		String actnum1 = textValue.getAttribute("value");
		int actNum1 = Integer.parseInt(actnum1);
		//Validating Total Individual Patient/Month is set to 820 and fetched valued is 820
		Assert.assertEquals(820, actNum1);
		textValue.click();
		textValue.clear();
		textValue.sendKeys("560");
		//slecting required chechbox for to make Total Recurring Reimbursement for all Patients Per Month:110700
		driver.findElement(By.xpath("//p[text()='CPT-99091']/../descendant::input")).click();
		driver.findElement(By.xpath("//p[text()='CPT-99453']/../descendant::input")).click();
		driver.findElement(By.xpath("//p[text()='CPT-99454']/../descendant::input")).click();
		driver.findElement(By.xpath("//p[text()='CPT-99474']/../descendant::input")).click();
		String totalValue = driver.findElement(By.xpath("//p[contains(text(),'Total R')]/p")).getText();
		//validationgTotal Recurring Reimbursement for all Patients Per Month is equal to $110700
		Assert.assertEquals("$110700", totalValue);
		driver.close();

	}

}
