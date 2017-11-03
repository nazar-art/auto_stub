package net.lelyak.edu.pages.abibas;

import net.lelyak.edu.core.annotations.Page;
import net.lelyak.edu.core.components.Button;
import net.lelyak.edu.core.components.TextField;
import net.lelyak.edu.core.components.WebFieldDecorator;
import net.lelyak.edu.pages.PageObject;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

@Page(title = "CommandCentreLoginPage")
public class LoginPage extends PageObject {

    public LoginPage() {
        successor = new HashMap<>();
        PageFactory.initElements(new WebFieldDecorator(getDriver()), this);
    }

    @Override
    public boolean exist() {
        return userName.isPresent()
                && password.isPresent()
                && submit.isPresent();
    }


    @FindBy(id = "username")
    protected TextField userName;

    @FindBy(id = "password")
    protected TextField password;

    @FindBy(name = "submit")
    protected Button submit;


    public boolean login(String login, String pass) {
        userName.clear();
        userName.sendText(login);
        password.clear();
        password.sendText(pass);
        submit.submit();
        return new WelcomePage().exist();
    }
}
