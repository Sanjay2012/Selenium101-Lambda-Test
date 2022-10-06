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

public class LambdaTest_1 extends BaseTest {

	@Test(testName = "Lambda Test First Scenario")
	public void EnterAndValidateMessage() throws InterruptedException {
		Reporter.log("Open LambdaTest’s Selenium Playground from", true);
		System.out.println("Step 1:Open LambdaTest’s Selenium Playground from");
		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		act = new Actions(driver);
		Reporter.log("<-----Lambda Test First Scenario Enter message and Validate it Started----->", true);
		Reporter.log("Click Simple Form Demo under Input Forms", true);
		WebElement formLink = driver.findElement(By.xpath("//a[text()='Simple Form Demo']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.elementToBeClickable(formLink));
		act.moveToElement(formLink).click(formLink).build().perform();

		Reporter.log("Validate that the URL contains simple-form-demo", true);
		soft.assertTrue(driver.getCurrentUrl().contains("simple-form-demo"));

		Reporter.log("Create a variable for a string value", true);
		String ExpectedMessage = "Welcome to LambdaTest";

		Reporter.log("Use this variable to enter values in the Enter Message text box.", true);
		WebElement textBox = driver.findElement(By.xpath("//input[@id='user-message']"));
		textBox.sendKeys(ExpectedMessage);
		Reporter.log("Click Get Checked Value", true);
		WebElement button = driver.findElement(By.id("showInput"));
		act.click(button).perform();
		Reporter.log("Validate whether the same text message is displayed in the right-hand panel under the Your Message section",
				true);

		//String ActualMessage = driver.findElement(By.cssSelector("#message")).getText();
		String ActualMessage =driver.findElement(By.xpath("//p[@id=\"message\"]")).getText();
		soft.assertEquals(ActualMessage, ExpectedMessage);

		Reporter.log("<------Lambda Test First Scenario Ended----->", true);

		soft.assertAll();
	}

}