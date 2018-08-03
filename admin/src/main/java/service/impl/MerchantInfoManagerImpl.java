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
        merchantInfo.setStatus("0"); //将状态设置为待审核
        MerchantInfo merchantInfo1 = merchantInfoDao.findMechantInfoByMerchantId(merchantInfo.getMerchantId());
        if(merchantInfo1==null){//该商家为未申请状态
            return merchantInfoDao.addMerchantInfo(merchantInfo);
        }else{ //商家为驳回状态
            return merchantInfoDao.updateMerchantInfo(merchantInfo);
        }
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
