package service.impl;

import dao.MerchantInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.MerchantInfo;
import service.MerchantInfoManager;

import java.util.List;

@Service
public class MerchantInfoManagerImpl implements MerchantInfoManager {

    @Autowired
    private MerchantInfoDao merchantInfoDao;

    @Override
    @Transactional
    public List<MerchantInfo> findAllMerchantInfo() {
        return merchantInfoDao.findAllMerchantInfos();
    }

    @Override
    @Transactional
    public MerchantInfo addMerchantInfo(MerchantInfo merchantInfo) {
        return merchantInfoDao.addMerchantInfo(merchantInfo);
    }

    @Override
    @Transactional
    public MerchantInfo updateMerchantInfo(MerchantInfo merchantInfo) {
        return merchantInfoDao.updateMerchantInfo(merchantInfo);
    }

    @Override
    @Transactional
    public List<MerchantInfo> findMechantInfosByStatus(String status) {
        return merchantInfoDao.findMechantInfosByStatus(status);
    }

    @Override
    @Transactional
    public MerchantInfo findMechantInfoByMerchantId(String merchantId) {
        return merchantInfoDao.findMechantInfoByMerchantId(merchantId);
    }
}
