package net.lelyak.edu.business;

import net.lelyak.edu.model.dto.AdminDTO;
import net.lelyak.edu.pages.abibas.LoginPage;

public class AdminBO {

    public boolean validateLoginField(AdminDTO adminDTO) {
        LoginPage loginPage = new LoginPage();
        return loginPage.login(adminDTO.getLogin(), adminDTO.getPass());
    }
}
