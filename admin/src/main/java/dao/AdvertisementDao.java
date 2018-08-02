package dao;

import pojo.Advertisement;

import java.util.List;

/**
 * @author JohnGao
 * @date 8/1/2018 9:05 PM
 */

public interface AdvertisementDao {
    public List<Advertisement> getAllAd();

    public Advertisement updateAd(Advertisement ad);

    public Advertisement addAd(Advertisement ad);

    public List<Advertisement> sendAds();

    public void deleteAdById(String i);

    public int findAdsCount();

    public List<Advertisement> getAdByPage(int begin, int end);

    public Advertisement findAdById(String id);
}
