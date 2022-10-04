package com.lambda.test;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.lambdat.test.base.BaseTest;

public class LambdaTest_2 extends BaseTest {

	@Test(testName = "Lambda Test second Scenario")
	public void HandlingSliderBar() throws InterruptedException {

		Reporter.log("Open LambdaTest’s Selenium Playground from", true);

		try {
			Reporter.log("Step 1:Open LambdaTests Selenium Playground form" , true);
			driver.get("https://www.lambdatest.com/selenium-playground/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Reporter.log("<---Lambda Test Second Scenario Handling Slider Bar Started ---->" , true);

		Reporter.log("Step 1: click Drag and Drop Sliders under Progress Bars and Sliders." , true);
		WebElement slider = driver.findElement(By.xpath("//a[normalize-space()='Drag & Drop Sliders']"));
		WebDriverWait wait;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			wait.until(ExpectedConditions.elementToBeClickable(slider));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", slider);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Reporter.log("Step 3: Select the slider Default value 15." , true);
		Reporter.log("Step 4: drag the bar to make it 95", true);
		try {
			Actions act = new Actions(driver);
			WebElement slider_bar = driver.findElement(By.xpath("//input[@value='15']"));
			soft.assertTrue(slider_bar.isDisplayed());

			act.moveToElement(slider_bar, 454, 600).click();
			int i = 0;
			while (i < 80) {
				slider_bar.sendKeys(Keys.RIGHT);
				++i;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Reporter.log("Step 5: validating whether the range value shows 95.", true);

		String actual = driver.findElement(By.xpath("//output[@id='rangeSuccess']")).getText();
		System.out.println("The slider moved horizontal right at value of :" + actual);
		soft.assertEquals(actual, "95");

		Reporter.log("Lambda Test Second Scenario Ended", true);
		soft.assertAll();
	}
}