package service;

import pojo.Advertisement;
import pojo.Page;

import java.util.List;

/**
 * @author JohnGao
 * @date 8/1/2018 9:05 PM
 */
public interface AdvertisementManager {
    public List<Advertisement> getAllAds() throws Exception;
    public void updateAd(int id,int state);
    public boolean addAd(Advertisement ad);
    public String sendAds();
    public boolean deleteAdById(int id);
    public Page<Advertisement> getAdsByPage(int currentPage, int pageSize);
}
