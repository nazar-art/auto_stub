package net.lelyak.edu.pages.abibas;

import net.lelyak.edu.core.annotations.Page;
import net.lelyak.edu.core.components.Button;
import net.lelyak.edu.core.components.NavigationLink;
import net.lelyak.edu.core.components.WebFieldDecorator;
import net.lelyak.edu.pages.PageObject;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

@Page(title = "WelcomePage")
public class WelcomePage extends PageObject {

    public WelcomePage() {
        successor = new HashMap<Class<? extends PageObject>, Button>();
        PageFactory.initElements(new WebFieldDecorator(getDriver()), this);
    }

    @Override
    public boolean exist() {
        return linkFeedback.isPresent()
                && linkIncidents.isPresent();
    }

    @FindBy(id = "new-feedback-button")
    protected NavigationLink linkFeedback;

    @FindBy(id = "new-incident-button")
    protected NavigationLink linkIncidents;

}
