/**
 * 
 */
package core;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author anderson.mann
 *
 */

public class DriverFactory {
	protected static AndroidDriver<MobileElement> driver;
	private static final String url = "https://www.sicredi.com.br/html/ferramenta/simulador-investimento-poupanca/";

	@BeforeMethod
	public void setup() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "4200cfd7dc9ba387");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "7.1.1");
		caps.setCapability("browserName", "Chrome");
		caps.setCapability("noReset", false);
		WebDriverManager.chromedriver().setup();
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.get(url);
	}	

	@AfterMethod
	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}