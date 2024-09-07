package ritiknagarproject;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class AppiumBasics extends BaseTest {

	@Test
	public void WifiSettingName() throws MalformedURLException, URISyntaxException {
		
		
		//Actual Automation
		//Xpath, id , accessibility id, classname, androidUIAutomator
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		
		//asserting the Alert Popup title
		Assert.assertEquals(alertTitle,"WiFi settings");
		
		driver.findElement(By.id("android:id/edit")).sendKeys("RitikWifi");
		driver.findElement(By.id("android:id/button1")).click();
		
		
	}
}
