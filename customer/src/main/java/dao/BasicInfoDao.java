package dao;

import pojo.BasicInfo;
import pojo.Pager;

import java.util.List;

public interface BasicInfoDao {
    public BasicInfo addBasicInfo(BasicInfo basicInfo);
    public BasicInfo updateBasicInfo(BasicInfo basicInfo);
    public List<BasicInfo> findAllBasicInfo();
    public Pager findAllBasicInfo(int curPage, int pageSize);
}
