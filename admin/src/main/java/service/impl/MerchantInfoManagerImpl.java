package service.impl;

import dao.MerchantInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.MerchantInfo;
import pojo.Page;
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

    @Override
    public Page<MerchantInfo> getMerchantInfoByPage(String status,int currentPage, int pageSize) {
        Page<MerchantInfo> page=new Page<MerchantInfo>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        int totalCount=merchantInfoDao.getMerchantInfoCount();
        page.setTotalCount(totalCount);
        int totalPage=0;
        if (totalCount%pageSize==0){
            totalPage=totalCount/pageSize;
        }else {
            totalPage=totalCount/pageSize+1;
        }
        page.setTotalPage(totalPage);
        int begin= (currentPage-1)*pageSize;
        List<MerchantInfo> list=merchantInfoDao.getMerchantInfosByPage(status,begin,pageSize);
        page.setDataList(list);
        return page;
    }
}
