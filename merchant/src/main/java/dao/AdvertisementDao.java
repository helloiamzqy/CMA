package dao;

import pojo.Advertisement;
import pojo.Merchant;

import java.util.List;

public interface AdvertisementDao {
    public Advertisement addAdvertisement(Advertisement advertisement);
    public List<Advertisement> findAdvertisementByMerchant(Merchant merchant);
    public List<Advertisement> findAdvertisementById(List<String> ids);
}
