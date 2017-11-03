package net.lelyak.edu.core.driver;

import net.lelyak.edu.core.common.Config;
import net.lelyak.edu.core.parallel.WebDriverPool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;


public class Driver {

    public final static WebDriverPool driver = new WebDriverPool(Config.getIntProperty(Config.WD_THREAD_COUNT));
    public final static ThreadLocal<ChromeDriverService> chromeService = new ThreadLocal<ChromeDriverService>();
//    public final static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
//    public final static ThreadLocal<ChromeDriverService> chromeService = new ThreadLocal<ChromeDriverService>();

    //	public static RemoteWebDriver driver;
    public static void getUrl(String url) {
        driver.get().get(url);
    }

    public static WebElement findElementById(String id) {
        return driver.get().findElement(By.id(id));
    }
}
