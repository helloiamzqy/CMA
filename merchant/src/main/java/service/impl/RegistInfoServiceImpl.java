package service.impl;

import dao.RegisterInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Merchant;
import pojo.RegisterInfo;
import service.RegistInfoManger;

/**
 * @author Dunn
 */
@Service
public class RegistInfoServiceImpl implements RegistInfoManger {
    @Autowired
    private RegisterInfoDao registerInfoDao;
    @Override
    public RegisterInfo getRegisterInfo(Merchant merchant) {
        return registerInfoDao.getRegisterInfo(merchant);
    }

    @Override
    @Transactional
    public RegisterInfo addRegisterInfo(RegisterInfo registerInfo) {
        registerInfoDao.addRegisterInfo(registerInfo);
        return registerInfo;
    }
}
