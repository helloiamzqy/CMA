package service.impl;

import dao.BasicInfoDao;
import dao.CommentDao;
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
    @Autowired
    private CommentDao commentDao;

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
    public BasicInfo updateBasicInfo(BasicInfo basicInfo,String mId) {
        Merchant merchant=new Merchant();
        merchant.setId(mId);
        basicInfo.setMerchant(merchant);
        return basicInfoDao.updateBasicInfo(basicInfo);

    }

    @Transactional
    @Override
    public BasicInfo findBasicInfoByMerchant(String mId) {
        Merchant merchant=new Merchant();
        merchant.setId(mId);
        BasicInfo basicInfo = new BasicInfo();
        basicInfo = basicInfoDao.findBasicInfoByMerchant(merchant);
        basicInfo.setRemark(commentDao.findRemarkByMerchant(merchant));
        System.out.println(basicInfo.getRemark());
        return  basicInfo;
    }


}
