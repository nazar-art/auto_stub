package net.lelyak.edu.dp.impl;

import net.lelyak.edu.dp.BaseDP;
import net.lelyak.edu.dp.DataProviderHelper;
import net.lelyak.edu.model.dao.impl.admin.IAdminDAO;
import net.lelyak.edu.model.dto.AdminDTO;
import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.List;

public class AdminDP {
    private static IAdminDAO adminDAO = BaseDP.daoFactory.getAdminDAO();

    @DataProvider(parallel = true)
    public static Iterator<Object[]> ValidateLogin() {
        List<AdminDTO> testData = adminDAO.findListById("ValidateLogin");
        return DataProviderHelper.toListObject(testData);
    }
}
