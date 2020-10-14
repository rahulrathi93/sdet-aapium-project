package appiumTests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class calculator {
	
	AppiumDriver<MobileElement> driver = null;
	
	  @BeforeMethod
	  public void BeforeMethod() throws MalformedURLException {
		  // Set the desired capabilities
		  
	      DesiredCapabilities caps = new DesiredCapabilities();  	
	      caps.setCapability("deviceId", "f6c0b729");	
	      caps.setCapability("deviceName", "OnePlus ONEPLUS A5000");	
	      caps.setCapability("platformName", "android");	
	      caps.setCapability("appPackage", "com.oneplus.calculator");	
	      caps.setCapability("appActivity", "com.oneplus.calculator.Calculator");
	      caps.setCapability("noReset", true);
		  
	      // Instantiate Appium Driver
	      URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
	      driver = new AndroidDriver<MobileElement>(appServer, caps);
		
	  }	

	
	
  @Test
  public void  multiply() {
	  driver.findElementById("com.oneplus.calculator:id/digit_7").click();
	  driver.findElementById("com.oneplus.calculator:id/op_mul").click();
	  driver.findElementById("com.oneplus.calculator:id/digit_9").click();
	  driver.findElementById("com.oneplus.calculator:id/eq").click();
	  
	  // Display result
	  
	  String result = driver.findElementById("com.oneplus.calculator:id/result").getText();
	  System.out.println(result);
	  Assert.assertEquals(result, "63");
        
  }
  
  

  

  @AfterMethod
  public void afterMethod() {
	  
	  driver.quit();
  }

}
