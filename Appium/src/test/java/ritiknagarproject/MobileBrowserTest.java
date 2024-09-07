package ritiknagarproject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class MobileBrowserTest extends BrowserBaseTest{

	@Test
	public void broserTest() {
		driver.get("http://google.com");
		driver.findElement(By.id("input")).sendKeys("You Tube");
		driver.findElement(By.id("input")).sendKeys(Keys.ENTER);
	}
}
