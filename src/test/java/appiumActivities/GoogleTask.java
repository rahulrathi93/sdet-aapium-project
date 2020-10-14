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

public class GoogleTask {
	AndroidDriver<MobileElement> driver = null;
	
	
  @Test
  public void addNewTask() {
	  String [] taskName = {"Complete Activity with Google Tasks", "Complete Activity with Google Keep", "Complete the second Activity Google Keep"};
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  MobileElement newTask = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.tasks:id/tasks_fab\")"));
	  for (String s: taskName ){
		  newTask.click();
		  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.tasks:id/add_task_title\")")).sendKeys(s);
		  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.tasks:id/add_task_done\")")).click();
		  
	  }
	  
	  List<MobileElement> createdTask = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.tasks:id/task_name\")"));
	  int count = createdTask.size();
	  System.out.println(count);
	  Assert.assertEquals(count, 3);
	  
  }
  @BeforeMethod
  public void beforeMethod() throws MalformedURLException {
	  // Set the desired capabilities
	  
      DesiredCapabilities caps = new DesiredCapabilities();  	
      caps.setCapability("deviceId", "f6c0b729");	
      caps.setCapability("deviceName", "OnePlus ONEPLUS A5000");	
      caps.setCapability("platformName", "android");	
      caps.setCapability("appPackage", "com.google.android.apps.tasks");	
      caps.setCapability("appActivity", ".ui.TaskListsActivity");
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
