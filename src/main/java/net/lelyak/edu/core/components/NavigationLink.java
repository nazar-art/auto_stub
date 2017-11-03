package net.lelyak.edu.core.components;

import net.lelyak.edu.core.common.Config;
import net.lelyak.edu.core.driver.Driver;
import net.lelyak.edu.core.localization.Localization;
import net.lelyak.edu.core.logging.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

public class NavigationLink extends AbstractPageElement {

    public NavigationLink(WebElement wrappedElement, String name, String page) {
        super(wrappedElement, name, page);
    }

    public void click() {
        try {
            if (wrappedElement != null) {

                String browserName = Config.getProperty(Config.BROWSER);

                if (browserName.equalsIgnoreCase(Config.B_CHROME)
                        || browserName.equalsIgnoreCase(Config.B_IE)) {

                    clickByJs();
                    Logger.logInfo(Localization.getMessage(Localization.CLICK_BUTTON, name, page));
                    Logger.logDebug("we passed to:" + Driver.driver.get().getCurrentUrl());
                    return;
                }

                wrappedElement.click();
                Logger.logDebug("we passed to:" + Driver.driver.get().getCurrentUrl());
                Logger.logInfo(Localization.getMessage(Localization.CLICK_BUTTON, name, page));

            } else {
                Logger.logError(Localization.getMessage(Localization.NO_BUTTON, name));
            }
        } catch (MoveTargetOutOfBoundsException e) {
            clickByJs();
            Logger.logInfo(Localization.getMessage(Localization.CLICK_BUTTON, name, page));
            Logger.logDebug("we passed to:" + Driver.driver.get().getCurrentUrl());

        } catch (ElementNotVisibleException e) {
            clickByJs();
            Logger.logInfo(Localization.getMessage(Localization.CLICK_BUTTON, name, page));
            Logger.logDebug("we passed to:" + Driver.driver.get().getCurrentUrl());
        }
    }
}
