package appiumActivities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GoogleChrome {
	AndroidDriver<MobileElement> driver = null;
	
  @Test
  public void toDo() {
	  
	  
	  
	  
	  
  }
  
  
  @BeforeMethod
  public void beforeMethod() throws MalformedURLException {
	  // Set the desired capabilities
	  
      DesiredCapabilities caps = new DesiredCapabilities();  	
      caps.setCapability("deviceId", "f6c0b729");	
      caps.setCapability("deviceName", "OnePlus ONEPLUS A5000");	
      caps.setCapability("platformName", "android");	
      caps.setCapability("appPackage", "com.android.chrome");	
      caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
      caps.setCapability("noReset", true);
	  
      // Instantiate Appium Driver
      URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
      driver = new AndroidDriver<MobileElement>(appServer, caps);   
	  driver.get("https://www.training-support.net/selenium");
	  
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
	  
  }

}
