package ritiknagarproject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class e_commerce_tc_1 extends BaseTestEcom{

	@Test
	public void FillForm() throws InterruptedException  {
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ritik Nagar");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//*[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Argentina\"]")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		/*When user not enter the data to validate the toast message*/
//		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("text");
//		Assert.assertEquals(toastMessage, "Please enter your name");
		
		//Search for the product and add to card
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
		int productCnt = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		
		String productNameInList = "";
		for(int i=0;i<productCnt;i++) {
			productNameInList = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(productNameInList.equalsIgnoreCase("Jordan 6 Rings")) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}
		}
		String addedToCartText =driver.findElement(By.xpath("//*[@text='ADDED TO CART']")).getText();
		Assert.assertEquals(addedToCartText, "ADDED TO CART");
		
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		//Wait until title present
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		
		//Submit the Product and Purchase the product
		String productNameInCart = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals(productNameInList, productNameInCart);
	}
}
