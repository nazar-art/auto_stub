package net.lelyak.edu.tests.impl.bank;

import net.lelyak.edu.pages.bank.BankLoginPage;
import net.lelyak.edu.tests.TestBase;
import org.testng.annotations.Test;

public class LoginTestITCase extends TestBase {

    @Test
    public void testLoginPageElements() throws Exception {
        BankLoginPage loginPage = new BankLoginPage();
        asserter.assertPass(loginPage.exist(),
                "page isn't open",
                "page is open");
    }


}
