package dao;

import pojo.BasicInfo;
import pojo.Merchant;

public interface BasicInfoDao {
    public BasicInfo addBasicInfo(BasicInfo basicInfo);
    public BasicInfo updateBasicInfo(BasicInfo basicInfo);
    public BasicInfo findBasicInfoByMerchant(Merchant merchant);
}
