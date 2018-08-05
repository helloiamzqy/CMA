package service;

import pojo.MerchantInfo;
import pojo.Page;

import java.util.List;

public interface MerchantInfoManager {

    public List<MerchantInfo> findAllMerchantInfo();
    public MerchantInfo addMerchantInfo(MerchantInfo merchantInfo);
    public MerchantInfo updateMerchantInfo(MerchantInfo merchantInfo);
    public List<MerchantInfo> findMechantInfosByStatus(String status);
    public MerchantInfo findMechantInfoByMerchantId(String merchantId);
    public Page<MerchantInfo> getMerchantInfoByPage(String status,int currentPage, int pageSize);
}
