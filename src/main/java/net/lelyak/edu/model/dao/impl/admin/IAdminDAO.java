package net.lelyak.edu.model.dao.impl.admin;

import net.lelyak.edu.model.dao.IDao;
import net.lelyak.edu.model.dto.AdminDTO;

import java.util.List;

public interface IAdminDAO extends IDao<AdminDTO> {
    @Override
    AdminDTO findById(String id);

    @Override
    List<AdminDTO> findListById(String id);
}
