package dao;

import pojo.MerchantInfo;

import java.util.List;

public interface MerchantInfoDao {
    public List<MerchantInfo> findAllMerchantInfos();
    public MerchantInfo addMerchantInfo(MerchantInfo merchantInfo);
    public MerchantInfo updateMerchantInfo(MerchantInfo merchantInfo);
    public List<MerchantInfo> findMechantInfosByStatus(String status);
    public MerchantInfo findMechantInfoByMerchantId(String merchantId);
    public List<MerchantInfo> getMerchantInfosByPage(String status,int begin,int end);
    public int getMerchantInfoCount();
}
