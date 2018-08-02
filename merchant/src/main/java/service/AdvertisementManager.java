package service;

import pojo.Advertisement;
import pojo.Merchant;

import javax.persistence.Query;
import java.util.List;

public interface AdvertisementManager {
    public Advertisement addAdvertisement(Advertisement advertisement,String mId);
    public List<Advertisement> findAdvertisementByMerchant(String mId);
    public List<Advertisement> findAdvertisementById(List<String> ids);

}
