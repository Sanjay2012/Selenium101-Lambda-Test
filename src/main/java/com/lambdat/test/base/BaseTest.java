package com.lambdat.test.base;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class BaseTest {
	protected RemoteWebDriver driver=null;
	protected String Status = "passed";
	protected Actions act;
	protected SoftAssert soft = new SoftAssert();
	protected Select select;
	protected WebDriverWait wait;
	protected JavascriptExecutor js;

	
	@BeforeMethod
	@Parameters(value = { "browser", "version", "platform" })
	public void setup(Method m, ITestContext ctx, String browser, String version, String platform)
			throws MalformedURLException {
		Reporter.log("<===== Start of Test Method =====>");
		String username = "wawaresanjay1";
		String accesskey = "lc2MNq8U6nqrMn4govcSLAkUKIaRJvhAVllyRkpDZpoVKz8kY0";
//		String username = "pandhareshital01";
//		String accesskey = "0tVLhBPdvYu2BvmeImD9BhTzG2A8xexkWu59hvO9HE7IclzEXJ";
		String hub = "@hub.lambdatest.com/wd/hub";
		
		Reporter.log("Setting desired Capabilities", true);
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName", browser);
		caps.setCapability("version", version);
		caps.setCapability("platform", platform);
		caps.setCapability("network", true); // To enable network logs
		caps.setCapability("visual", true); // To enable step by step screenshot
		caps.setCapability("video", true); // To enable video recording
		caps.setCapability("console", true); // To capture console logs
		caps.setCapability("build", "Selenium101-LambdaTest");
		caps.setCapability("name", m.getName() + this.getClass().getName());
		caps.setCapability("plugin", "git-testng");
		
		Reporter.log("Connecting to hub/ Grid", true);
		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + hub), caps);
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	@BeforeTest
	@Parameters("url")
	public void setUrl(String url) {
		
		Reporter.log("Step 1:Open LambdaTest’s Selenium Playground from",true);
		try {
		    driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
			 act=new Actions(driver);
		     wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown() {
		Reporter.log("<+======Ending the Test Method =====>", true);
		Reporter.log("Closing the browser session", true);
		driver.executeScript("lambda-status=" + Status);
		driver.quit();
	}

}
