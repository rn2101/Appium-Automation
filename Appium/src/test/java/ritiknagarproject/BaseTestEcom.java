package ritiknagarproject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTestEcom {;
	
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public Actions action;
	
	@BeforeClass
	public void ConfigAppium() throws MalformedURLException, URISyntaxException {
		service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\nriti\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")).withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("RitikEmulator");
		options.setChromedriverExecutable("C:\\Users\\nriti\\Downloads\\chromedriver_win32");
		options.setApp("C:\\Users\\nriti\\eclipse-workspace-2\\Appium\\src\\test\\java\\resources\\General-Store.apk");
		
		
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	public double getFormattedAmount(String amount) {
		return Double.parseDouble(amount.substring(1));
	}
	
	/**
	 * This function is used to long press
	 * @param ele
	 */
	public void longPressAction(WebElement ele) {
		Actions action = new Actions(driver);
		action.clickAndHold(ele).pause(Duration.ofSeconds(2))
	       .release()
	       .perform();
	}
	
	/**
	 * This function is used to do hardware back in the android
	 */
	public void androidHardwareBack() {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}
	
	@AfterClass
	public void teatDown() {
		driver.quit();
		service.stop();
		//stop service
	}
	
}