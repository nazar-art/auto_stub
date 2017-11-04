package net.lelyak.edu.core.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import net.lelyak.edu.core.common.Config;
import net.lelyak.edu.core.logging.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Set;
import java.util.logging.Level;

public enum DriversEnum {

    FF {
        @Override
        public RemoteWebDriver getDriver() {
            String os = System.getenv().get("OS");
            Logger.logDebug("OS is: " + os);

            //if you're going to use more than one OS, you should make this switchable based on OS.
            Path path = FileSystems.getDefault().getPath("3rdParty/geckodriver/geckodriver");
            System.setProperty("webdriver.gecko.driver",path.toString());

            if (os.contains("Mac")) {
                System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
            } else if (os.contains("Windows")) {
                Logger.logDebug("Set geckodriver path");
                System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");

//                capabilities.setCapability("firefox_binary","C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
//                capabilities.setCapability("gecko", true);
//                capabilities.setCapability("marionette", true);
            }

//            FirefoxProfile firefoxProfile = new FirefoxProfile();
//            firefoxProfile.setPreference("browser.cache.disk.enable", false);
//            firefoxProfile.setPreference("browser.cache.memory.enable", false);
//            firefoxProfile.setPreference("browser.cache.offline.enable", false);
//            firefoxProfile.setPreference("network.http.use-cache", false);
//            firefoxProfile.setPreference("browser.download.folderList", 2);
//            firefoxProfile.setPreference("intl.accept_languages", "en-US");
//            firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
////            firefoxProfile.setPreference("browser.download.dir", "D:\\");
//            firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
//                    "text/csv, application/pdf");
//
//            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
////            capabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
            FirefoxOptions options = new FirefoxOptions()
                    .addPreference("browser.startup.page", 1)
                    .addPreference("browser.startup.homepage", Config.getProperty(Config.TEST_HOST));
            return new FirefoxDriver(options);
        }
    },

    ANDROIDHYBRID {
        @Override
        public RemoteWebDriver getDriver() {
            try {
                DesiredCapabilities capabilities = DesiredCapabilities.android();
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
//                capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.easyfinancial.indirect.mobile");
//                capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, ".MobileApp");

                URL url = new URL("http://127.0.0.1:4723/wd/hub");

                AppiumDriver<WebElement> driver = null;
                try {
                    driver = new AndroidDriver<WebElement>(url, capabilities);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println(driver.getContext());
                Set<String> contextNames = driver.getContextHandles();
                for (String contextName : contextNames) {
                    System.out.println(contextName);
                    try {

                        if (contextName.contains("WEBVIEW")) {
                            driver.context(contextName);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        driver.context("WEBVIEW_0");
                    }
                }
                return driver;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    },

    ANDROID {
        @Override
        public RemoteWebDriver getDriver() {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Browser");
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
//            capabilities.setCapability(MobileCapabilityType.DEVICE_READY_TIMEOUT, "30");
            URL url;
            try {
                url = new URL("http://127.0.0.1:4723/wd/hub");

                RemoteWebDriver remoteWebDriver = new RemoteWebDriver(url,
                        capabilities);
                return remoteWebDriver;
            } catch (MalformedURLException e) {

                e.printStackTrace();
            }
            return null;
        }
    },

    CHROME {
        @Override
        public RemoteWebDriver getDriver() {
            /*String path = DriversEnum.class.getClassLoader()
                    .getResource("3rdParty/chrome/chromedriver.exe").getPath();
            System.setProperty("webdriver.chrome.driver", path);*/

            File chromeFile = new File(Config.getProperty(Config.CHROME_PATH));
            System.setProperty("webdriver.chrome.driver", chromeFile.getAbsolutePath());

            ChromeDriverService service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(chromeFile)
                    .usingAnyFreePort().build();
            Driver.chromeService.set(service);
            try {
                service.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            return new ChromeDriver(capabilities);
        }

    },

    IE {
        @Override
        public RemoteWebDriver getDriver() {
//            String path = DriversEnum.class.getClassLoader()
//                    .getResource("IEDriverServer.exe").getPath();

            File ieFile = new File(Config.getProperty(Config.IE_PATH));
            System.setProperty("webdriver.ie.driver", ieFile.getAbsolutePath());

            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(
                    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                    true);
            capabilities.setJavascriptEnabled(true);
            return new InternetExplorerDriver(capabilities);
        }

    },

    QT {
        @Override
        public RemoteWebDriver getDriver() {
            RemoteWebDriver remoteWebDriver = null;

            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("maximize", true);
            //specify reuseUI to have WebDriver terminate any previous session and reuse its windows
            cap.setCapability("reuseUI", true);

            //specify to select the first found window
            cap.setCapability("browserStartWindow", "*");

            LoggingPreferences logs = new LoggingPreferences();
            Level level = Level.ALL;
            logs.enable(LogType.DRIVER, level);
            logs.enable(LogType.BROWSER, level);
            logs.enable(LogType.PERFORMANCE, level);
            // specify log level
            cap.setCapability(CapabilityType.LOGGING_PREFS, logs);

            try {
//                URL url = new URL("http://localhost:9517");
//                URL url = new URL(Config.getProperty("qt.app.url"));
                URL url = new URL("http://localhost:9530");
                remoteWebDriver = new RemoteWebDriver(url, cap);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return remoteWebDriver;
        }
    };

    public abstract RemoteWebDriver getDriver();
}
