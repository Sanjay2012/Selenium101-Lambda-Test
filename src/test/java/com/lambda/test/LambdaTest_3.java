package com.lambda.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.lambdat.test.base.BaseTest;

public class LambdaTest_3 extends BaseTest{

	@Test(testName = "Lambda Test Third Scenario")
	public void FormFilling() throws InterruptedException {
		try {
			Reporter.log("Step 1:Open LambdaTest’s Selenium Playground from", true);
   			
			driver.get("https://www.lambdatest.com/selenium-playground/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		act = new Actions(driver);
		Reporter.log("<-----Third Scenario Form filling started---->", true);
		Reporter.log("Step 2 : click on Input Form Submit link under Input Forms.", true);
		
		WebElement inputFormLink = driver.findElement(By.xpath("//a[contains(text(),'Input Form')]"));

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			wait.until(ExpectedConditions.elementToBeClickable(inputFormLink));
			act.moveToElement(inputFormLink).click(inputFormLink).build().perform();

		} catch (Exception e) {
			e.printStackTrace();
		}

		Reporter.log("Step 3: Click on Submit without filling in any information in the form.", true); 
		WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			wait.until(ExpectedConditions.presenceOfElementLocated((By) submitButton));
			submitButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Reporter.log("Step 4: Assert Please fill in the fields error message."); 
		boolean actualError = driver.findElement(By.xpath("//input[@id='name']")).getAttribute("validationMessage") != null;
		System.out.println(actualError);
		soft.assertTrue(actualError);

		Reporter.log("Step 5 : Fill in Name, Email, and other fields. locators of all form fields", true); 
		
		driver.findElement(By.cssSelector("#name")).sendKeys("Sanjay Waware");

		driver.findElement(By.id("inputEmail4")).sendKeys("sanjay.waware@zensar.com");

		driver.findElement(By.xpath("//input[@id='inputPassword4']")).sendKeys("Pass@123");

		driver.findElement(By.cssSelector("#company")).sendKeys("Zensar Technologies Ltd.");

		driver.findElement(By.cssSelector("#websitename")).sendKeys("https://www.zensar.com");

		try {
			WebElement countryDropdown = driver.findElement(By.xpath("//select[@name='country']"));

			act.moveToElement(countryDropdown).click().sendKeys("United States");
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.findElement(By.cssSelector("#inputCity")).sendKeys("Pune,Maharashtra");

		driver.findElement(By.cssSelector("#inputAddress1")).sendKeys("Evon IT Park, Kharadi, Pune");

		driver.findElement(By.cssSelector("#inputAddress2")).sendKeys("Evon IT Park, Kharadi, Pune");

		driver.findElement(By.cssSelector("#inputState")).sendKeys("Maharashtra");

		driver.findElement(By.cssSelector("#inputZip")).sendKeys("10038");
		
		Reporter.log("Step 6: click Submit Button after fill the form", true);
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			wait.until(ExpectedConditions.presenceOfElementLocated((By) submitButton));
			submitButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Reporter.log("STep 7: validate the success message on screen ---> Thanks for contacting us, will get back to you shortly.", true);

		soft.assertTrue(driver.getPageSource().contains("Thanks for contacting us, we will get back to you shortly."));
		Reporter.log("Lambda Test Form filling and Submission Ended", true);
		soft.assertAll();
	}

}