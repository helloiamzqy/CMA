package service;

import pojo.MerchantInfo;

import java.util.List;

public interface MerchantInfoManager {

    public List<MerchantInfo> findAllMerchantInfo();
    public MerchantInfo addMerchantInfo(MerchantInfo merchantInfo);
    public MerchantInfo updateMerchantInfo(MerchantInfo merchantInfo);
}
