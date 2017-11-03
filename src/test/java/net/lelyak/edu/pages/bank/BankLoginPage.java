package net.lelyak.edu.pages.bank;

import net.lelyak.edu.core.annotations.Page;
import net.lelyak.edu.core.components.Button;
import net.lelyak.edu.core.components.TextField;
import net.lelyak.edu.core.components.WebFieldDecorator;
import net.lelyak.edu.pages.PageObject;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

@Page(title = "Parabank Login Page")
public class BankLoginPage extends PageObject {

    public BankLoginPage() {
        successor = new HashMap<>();
        PageFactory.initElements(new WebFieldDecorator(getDriver()), this);
    }

    @Override
    public boolean exist() {
        return loginBtn.isPresent() 
                && loginInput.isPresent() 
                && passwordInput.isPresent();
    }

    @FindBy(name = "username")
    private TextField loginInput;
    
    @FindBy(name = "password")
    private TextField passwordInput;
    
    @FindBy(xpath = "//input[@value='Log In']")
    private Button loginBtn;

    public boolean login(String login, String pass) {
        loginInput.clear();
        loginInput.sendText(login);
        passwordInput.clear();
        passwordInput.sendText(pass);
        loginBtn.submit();
        return new HomePage().exist();
    }
}
