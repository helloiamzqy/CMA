package service.impl;

import dao.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Manager;
import service.AdminManager;

/**
 * @author Carrie
 * @date 2018-8-3 17:40
 */
@Service
public class AdminManagerImpl implements AdminManager {

    @Autowired
    private ManagerDao managerDao;

    @Override
    @Transactional
    public Manager findManager(Manager manager) {

        return managerDao.findManager(manager);
    }
}
