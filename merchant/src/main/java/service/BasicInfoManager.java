package service;

import pojo.BasicInfo;

public interface BasicInfoManager {
    public BasicInfo addBasicInfo(BasicInfo basicInfo,String mId);
    public BasicInfo updateBasicInfo(BasicInfo basicInfo,String mId);
    public BasicInfo findBasicInfoByMerchant(String mId);
}
