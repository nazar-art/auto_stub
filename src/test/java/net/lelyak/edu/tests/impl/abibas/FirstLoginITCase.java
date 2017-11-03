package net.lelyak.edu.tests.impl.abibas;

import net.lelyak.edu.business.AdminBO;
import net.lelyak.edu.dp.impl.AdminDP;
import net.lelyak.edu.model.dto.AdminDTO;
import net.lelyak.edu.tests.TestBase;
import org.testng.annotations.Test;

public class FirstLoginITCase extends TestBase {

    @Test(dataProviderClass = AdminDP.class, dataProvider = "ValidateLogin")
    public void validateLoginAndPassFieldFormat(AdminDTO adminDTO) {
        AdminBO adminBO = new AdminBO();
        asserter.assertFail(adminBO.validateLoginField(adminDTO),
                "Fail: Login field is not valid.",
                "Pass: Login field is valid.");
    }

}
