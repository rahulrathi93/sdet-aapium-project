package appiumActivities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GoogleKeep {
	AndroidDriver<MobileElement> driver = null;
	
  	
  @Test
  public void addNote() {
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  List<MobileElement> availableTasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/browse_text_note\")"));
	  int count = availableTasks.size();
	  System.out.println(count);
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/new_note_button\")")).click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/editable_title\")")).sendKeys("Sample Title 1");
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/edit_note_text\")")).sendKeys("Sample Description 1");
	  driver.findElementByAccessibilityId("Open navigation drawer").click();
	  availableTasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/browse_text_note\")"));
	  int newCount = availableTasks.size();
	  System.out.println(newCount);
	  Assert.assertEquals((newCount-count) , 1);
	  	  
  }
  
  @Test
  public void addNoteWithReminder(){
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  // check initial count for number of available reminders
	  driver.findElementByAccessibilityId("Open navigation drawer").click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/drawer_navigation_reminders\")")).click();
	  List<MobileElement> availableReminders = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/browse_text_note\")"));
	  int count = availableReminders.size();
	  System.out.println(count);
	  driver.findElementByAccessibilityId("Open navigation drawer").click();
	  
	  // add new note with reminder
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/drawer_navigation_active\")")).click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/new_note_button\")")).click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/editable_title\")")).sendKeys("Sample Title 2 ");
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/edit_note_text\")")).sendKeys("Sample Description 2");
	  
	  // add reminder
	  driver.findElementByAccessibilityId("Reminder").click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/time_spinner\")")).click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/reminder_time_afternoon\")")).click();
	  //driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/reminder_time_night\")")).click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/save\")")).click();
	  driver.findElementByAccessibilityId("Open navigation drawer").click();
	  
	  // check whether the reminder is available
	  
	  driver.findElementByAccessibilityId("Open navigation drawer").click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/drawer_navigation_reminders\")")).click();
	  availableReminders = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/browse_text_note\")"));
	  int newCount = availableReminders.size();
	  System.out.println(newCount);
	  Assert.assertEquals((newCount-count), 1);
	   
	  
	  
  }
  
  
  
  
  @BeforeMethod
  public void beforeMethod() throws MalformedURLException {
	  // Set the desired capabilities
	  
      DesiredCapabilities caps = new DesiredCapabilities();  	
      caps.setCapability("deviceId", "f6c0b729");	
      caps.setCapability("deviceName", "OnePlus ONEPLUS A5000");	
      caps.setCapability("platformName", "android");	
      caps.setCapability("appPackage", "com.google.android.keep");	
      caps.setCapability("appActivity", ".activities.BrowseActivity");
      caps.setCapability("noReset", true);
	  
      // Instantiate Appium Driver
      URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
      driver = new AndroidDriver<MobileElement>(appServer, caps);      
	  
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
