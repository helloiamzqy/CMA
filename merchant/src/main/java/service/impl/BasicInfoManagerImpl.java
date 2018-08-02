package service.impl;

import dao.BasicInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.BasicInfo;
import pojo.Merchant;
import service.BasicInfoManager;

@Service
public class BasicInfoManagerImpl implements BasicInfoManager {
    @Autowired
    private BasicInfoDao basicInfoDao;

    @Transactional
    @Override
    public BasicInfo addBasicInfo(BasicInfo basicInfo,String mId) {
        Merchant merchant=new Merchant();
        merchant.setId(mId);
        basicInfo.setMerchant(merchant);
        return basicInfoDao.addBasicInfo(basicInfo);
    }

    @Transactional
    @Override
    public BasicInfo updateBasicInfo(BasicInfo basicInfo) {
        return basicInfoDao.updateBasicInfo(basicInfo);
    }


}
