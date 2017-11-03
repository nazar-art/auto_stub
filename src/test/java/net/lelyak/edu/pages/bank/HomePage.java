package net.lelyak.edu.pages.bank;

import net.lelyak.edu.core.annotations.Page;
import net.lelyak.edu.core.components.TextContainer;
import net.lelyak.edu.core.components.WebFieldDecorator;
import net.lelyak.edu.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

@Page(title = "Bank Home Page")
public class HomePage extends PageObject {

    public HomePage() {
        successor = new HashMap<>();
        PageFactory.initElements(new WebFieldDecorator(getDriver()), this);
    }

    @Override
    public boolean exist() {
        return title.isPresent();
    }

    @FindBy(css = ".title")
    private TextContainer title;

    public String getTitleText() {
        return title.getText();
    }
}
