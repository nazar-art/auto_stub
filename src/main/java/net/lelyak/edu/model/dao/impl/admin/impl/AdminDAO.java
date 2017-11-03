package net.lelyak.edu.model.dao.impl.admin.impl;

import net.lelyak.edu.core.common.XlsReader;
import net.lelyak.edu.model.dao.impl.admin.IAdminDAO;
import net.lelyak.edu.model.dao.impl.XlsHelper;
import net.lelyak.edu.model.dto.AdminDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminDAO implements IAdminDAO {

    private XlsReader xls;

    @Override
    public AdminDTO findById(String id) {
        xls = new XlsReader("InputData.xlsx", "Admin");
        Map<String, String> testData = xls.getDataById(id);

        AdminDTO adminNew = new AdminDTO();
        XlsHelper.fillObject(adminNew, testData);

        return adminNew;
    }

    @Override
    public List<AdminDTO> findListById(String id) {
        xls = new XlsReader("InputData.xlsx", "Admin");
        List<Map<String, String>> testData = xls.getDataListById(id);
        if (testData != null && !testData.isEmpty()) {
            List<AdminDTO> adminData = new ArrayList<AdminDTO>();
            for (Map<String, String> dataItem : testData) {
                AdminDTO adminNew = new AdminDTO();
                XlsHelper.fillObject(adminNew, dataItem);

                adminData.add(adminNew);
            }
            return adminData;
        }
        return null;
    }
}
