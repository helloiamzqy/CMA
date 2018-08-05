package service.impl;

import dao.BasicInfoDao;
import org.apache.kahadb.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.BasicInfo;
import pojo.Merchant;
import pojo.Pager;
import service.BasicInfoService;

import java.util.List;

@Service
public class BasicInfoServiceImpl implements BasicInfoService {
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

    @Override
    public List<BasicInfo> findAllBasicInfo() {
        return basicInfoDao.findAllBasicInfo();
    }

    @Override
    public Pager findAllBasicInfo(int curPage, int pageSize) {
        return basicInfoDao.findAllBasicInfo(curPage,pageSize);
    }

    @Override
    public BasicInfo findBasicInfoById(String id) {
        return basicInfoDao.findBasicInfoById(id);
    }
}