package service.impl;

import dao.AdvertisementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Advertisement;
import pojo.Merchant;
import service.AdvertisementManager;

import java.util.List;

@Service
public class AdvertisementManagerImpl implements AdvertisementManager {
    @Autowired
    private AdvertisementDao advertisementDao;
    @Transactional
    @Override
    public Advertisement addAdvertisement(Advertisement advertisement, String mId) {
        Merchant merchant=new Merchant();
        merchant.setId(mId);
        advertisement.setMerchant(merchant);
        return advertisementDao.addAdvertisement(advertisement);
    }

    @Override
    public List<Advertisement> findAdvertisementByMerchant(String mId) {
        Merchant merchant=new Merchant();
        merchant.setId(mId);
        return advertisementDao.findAdvertisementByMerchant(merchant);
    }

    @Override
    public List<Advertisement> findAdvertisementById(List<String> ids) {
        return advertisementDao.findAdvertisementById(ids);
    }
}
