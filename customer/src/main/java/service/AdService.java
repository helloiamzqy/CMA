package service;

import pojo.Advertisement;
import pojo.BasicInfo;
import pojo.Pager;

import java.util.List;

public interface AdService {
    public List<Advertisement> findAd();
}
