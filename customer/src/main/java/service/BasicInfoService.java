package service;

import pojo.BasicInfo;
import pojo.Pager;

import java.util.List;

public interface BasicInfoService {
    public BasicInfo addBasicInfo(BasicInfo basicInfo, String mId);
    public BasicInfo updateBasicInfo(BasicInfo basicInfo);
    public List<BasicInfo> findAllBasicInfo();
    public Pager findAllBasicInfo(int curPage, int pageSize);
    public BasicInfo findBasicInfoById(String id);
    public BasicInfo findBasicInfoWithRankById(String id);
}
