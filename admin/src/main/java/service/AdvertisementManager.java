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
    public Advertisement updateAd(String id,String state);
    public Advertisement addAd(Advertisement ad);
  //  public String sendAds();
    public List<Advertisement> sendAds();
    public void deleteAdById(String id);
    public Page<Advertisement> getAdsByPage(int currentPage, int pageSize);
    public Advertisement updateAdvertisement(Advertisement advertisement);
}
