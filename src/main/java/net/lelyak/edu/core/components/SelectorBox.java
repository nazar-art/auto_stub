package net.lelyak.edu.core.components;

import net.lelyak.edu.core.localization.Localization;
import net.lelyak.edu.core.logging.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class SelectorBox extends AbstractPageElement {

    public SelectorBox(WebElement wrappedElement, String name, String page) {
        super(wrappedElement, name, page);
    }

    public void selectRandomValue() {
        if (wrappedElement != null) {
            highlightElement();
            String value = "";
            Select select = new Select(wrappedElement);
            logAllOptions(select);
            Integer size = select.getOptions().size();
            Random random = new Random();
            Integer index = random.nextInt(size - 2) + 1;
            try {
                select.selectByIndex(index);
                value = select.getFirstSelectedOption().getText();
                Logger.logInfo(Localization.getMessage(
                        Localization.SELECT_RANDOM, value, name, page));
            } catch (Exception e) {
                // log
            }
        } else {
            Logger.logError(Localization.getMessage(Localization.NO_SELECT,
                    name, page));
        }
    }

    protected void logAllOptions(Select select) {
        List<WebElement> options = select.getOptions();
        StringBuilder builder = new StringBuilder("option = ");
        for (WebElement option : options) {
            builder.append(option.getText() + ", ");
        }
        Logger.logInfo(builder.toString());
    }

    public void selectValue(String valueToSelect) {
        boolean selected = false;
        if (wrappedElement != null) {
            highlightElement();
            Select select = new Select(wrappedElement);
            List<WebElement> options = select.getOptions();
            try {
                int index = -1;
                for (WebElement option : options) {
                    index = index + 1;
                    String optionText = option.getText();
                    if (optionText.contains(valueToSelect)
                            || optionText.equalsIgnoreCase(valueToSelect)) {
                        option.click();
                        Logger.logInfo(Localization.getMessage(
                                Localization.SELECT_VALUE, valueToSelect, name,
                                page));
                        selected = true;
                        break;
                    }
                    /*else {
                        select.selectByIndex(index);
						options.get(0).click();
					}*/
                }
            } catch (NullPointerException e) {
                int index = options.size() - 1;
                options.get(index).click();
            }

            if (!selected) {
                Logger.logError(Localization.getMessage(
                        Localization.SELECT_DATA_WRONG, valueToSelect, name,
                        page));
            }
        } else {
            Logger.logError(Localization
                    .getMessage(Localization.NO_SELECT, name, page));
        }
    }

    public boolean isInOptions(String value) {
        if (wrappedElement != null) {
            highlightElement();
            Select select = new Select(wrappedElement);
            List<WebElement> options = select.getOptions();

            for (WebElement option : options) {
                if (option.getText().equals(value) && option.isDisplayed()) {
                    return true;
                }

            }
            return false;
        }
        Logger.logError(Localization.getMessage(Localization.NO_SELECT, name,
                page));
        return false;

    }

    public String getSelectedOptionText() {
        if (wrappedElement != null) {
            highlightElement();
            Select select = new Select(wrappedElement);
            String value = select.getFirstSelectedOption().getText();
            Logger.logInfo(Localization.getMessage(
                    Localization.SELECT_GET_TEXT, value, name, page));
            return value;
        } else {
            Logger.logError(Localization.getMessage(Localization.NO_SELECT,
                    name, page));
        }
        return null;
    }
}
