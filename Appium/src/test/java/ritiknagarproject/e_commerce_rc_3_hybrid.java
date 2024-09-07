package ritiknagarproject;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;

public class e_commerce_rc_3_hybrid extends BaseTestEcom{
	
	@Test
	public void hybridApp() throws InterruptedException  {

	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ritik Nagar");
	driver.hideKeyboard();
	driver.findElement(By.xpath("//*[@text='Female']")).click();
	driver.findElement(By.id("android:id/text1")).click();
	driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
	driver.findElement(By.xpath("//android.widget.TextView[@text=\"Argentina\"]")).click();
	driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	
	driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
	driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

	
	driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	
	//Wait until title present
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
	
	List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
	int count = productPrices.size();
	double totalSum=0;
	for(int i=0;i<count;i++) {
		String amountString = productPrices.get(i).getText();
		double price = getFormattedAmount(amountString);
		totalSum = totalSum+price;
	}
	String displaySum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
	double displayFormattedAmount = getFormattedAmount(displaySum);
	Assert.assertEquals(totalSum, displayFormattedAmount);
	
	WebElement termAndCondition = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
	longPressAction(termAndCondition);
	
	String alertTitle = driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
	Assert.assertEquals(alertTitle, "Terms Of Conditions");
	driver.findElement(By.id("android:id/button1")).click();
	
	driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
	driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
	
	//Hybrid App-- Google Page -> 
	Thread.sleep(6000);
	//Set<String> contexts = driver.getContextHandles();						//To get the context Name use this 2 line
	//for (String contextName : contexts) System.out.println(contextName);
	driver.context("WEBVIEW_com.androidsample.generalstore"); //chromeDriver
	driver.findElement(By.id("input")).sendKeys("You Tube");
	driver.findElement(By.id("input")).sendKeys(Keys.ENTER);
	androidHardwareBack();
	driver.context("NATIVE_APP");
	
  }
}