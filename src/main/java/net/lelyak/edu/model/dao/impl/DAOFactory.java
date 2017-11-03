package net.lelyak.edu.model.dao.impl;

import net.lelyak.edu.model.dao.impl.admin.IAdminDAO;
import org.springframework.beans.factory.annotation.Autowired;

//@Getter
public class DAOFactory {

    @Autowired
    private IAdminDAO adminDAO;

    public IAdminDAO getAdminDAO() {
        return adminDAO;
    }

}
